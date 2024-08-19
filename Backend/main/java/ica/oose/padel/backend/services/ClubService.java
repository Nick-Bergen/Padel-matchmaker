package ica.oose.padel.backend.services;

import ica.oose.padel.backend.persistence.Club;
import ica.oose.padel.backend.persistence.repositories.ClubRepository;
import ica.oose.padel.backend.services.dto.ClubDTO;
import ica.oose.padel.backend.services.dto.ClubsDTO;
import ica.oose.padel.backend.services.exceptions.DuplicateEntityException;
import ica.oose.padel.backend.services.exceptions.InvalidInputException;
import ica.oose.padel.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class ClubService {

    private ClubRepository clubRepository;

    @Autowired
    public void setClubRepository(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    //Looks up a club based on it's name and returns the club info as a ClubDTO
    public ClubDTO getClubByName(String name) {
        var club = clubRepository.findClubByName(name);
        if (club == null) {
            throw new ResourceNotFoundException("Club");
        }

        return new ClubDTO(club.getName(), club.getLocation(), club.getOpeningHours());
    }

    //Insert a new club into the database with the provided information and then returns a ClubDTO with the club info
    public ClubDTO insertNewClub(String name, String city, String openinghours) {
        if (name == null) {
            throw new InvalidInputException("name");
        }
        if (city == null) {
            throw new InvalidInputException("city");
        }
        if (openinghours == null) {
            throw new InvalidInputException("openinghours");
        }
        if (clubRepository.findClubByName(name) != null) {
            throw new DuplicateEntityException(name);
        }

        Club newClub = new Club(name, city, openinghours);
        clubRepository.save(newClub);
        return new ClubDTO(name);
    }

    //Returns all clubs saved in the database as a ClubsDTO
    public ClubsDTO getAllClubs() {
        var clubs = clubRepository.findAll();
        return new ClubsDTO(clubs.stream()
                                    .map(club -> new ClubDTO(club.getName(), club.getLocation(), club.getOpeningHours()))
                                    .collect(Collectors.toList())
                            );
    }
}