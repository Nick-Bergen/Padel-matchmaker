package ica.oose.padel.backend.persistence.repositories;

import ica.oose.padel.backend.persistence.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, String> {
    List<Field> findAll();

    List<Field> findFieldsByClubName(String club_name);
}
