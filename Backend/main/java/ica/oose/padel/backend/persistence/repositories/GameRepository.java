package ica.oose.padel.backend.persistence.repositories;

import ica.oose.padel.backend.persistence.Game;
import ica.oose.padel.backend.services.dto.PlayerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
    List<Game> findGamesByMadeBy(String playerAlias);
}
