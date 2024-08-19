package ica.oose.padel.backend.services;

import ica.oose.padel.backend.persistence.Club;
import ica.oose.padel.backend.persistence.Field;
import ica.oose.padel.backend.persistence.repositories.ClubRepository;
import ica.oose.padel.backend.persistence.repositories.FieldRepository;
import ica.oose.padel.backend.services.dto.FieldDTO;
import ica.oose.padel.backend.services.dto.FieldsDTO;
import ica.oose.padel.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class FieldService {

    ClubRepository clubRepository;
    FieldRepository fieldRepository;

    @Autowired
    public void setClubRepository(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Autowired
    public void setFieldRepository(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public FieldDTO insertNewField(String name, String clubName) {
        Club club = clubRepository.findClubByName(clubName);
        if (club == null) {
            throw new ResourceNotFoundException("Club");
        }
        Field field = new Field(name, clubName);
        fieldRepository.save(field);

        return new FieldDTO(name, clubName);
    }

    public FieldsDTO getAllFieldsFromClub(String club) {
        var fields = fieldRepository.findFieldsByClubName(club).stream()
                .map(field -> new FieldDTO(field.getName(), field.getClubName()))
                .collect(Collectors.toList());

        if (fields.isEmpty()) {
            throw new ResourceNotFoundException("Club");
        }

        return new FieldsDTO(fields);
    }
}