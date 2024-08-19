package ica.oose.padel.backend.persistence.repositories;

import ica.oose.padel.backend.persistence.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository <Player, String> {
    List<Player> findAll();

    Player findPlayerByAlias(String alias);

    List<Player> findPlayersByRanking(int ranking);
}
