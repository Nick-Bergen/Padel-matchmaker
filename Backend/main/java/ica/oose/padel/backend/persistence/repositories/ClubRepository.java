package ica.oose.padel.backend.persistence.repositories;

import ica.oose.padel.backend.persistence.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, String> {
    Club findClubByName(String name);
}
