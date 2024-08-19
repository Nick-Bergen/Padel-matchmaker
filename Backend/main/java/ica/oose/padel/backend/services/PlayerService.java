package ica.oose.padel.backend.services;

import ica.oose.padel.backend.persistence.Player;
import ica.oose.padel.backend.persistence.repositories.PlayerRepository;
import ica.oose.padel.backend.services.dto.PlayerDTO;
import ica.oose.padel.backend.services.dto.PlayersDTO;
import ica.oose.padel.backend.services.exceptions.InvalidInputException;
import ica.oose.padel.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    //Retrieves single player based on his unique alias
    public PlayerDTO getPlayerByAlias(String alias) {
        var player = playerRepository.findPlayerByAlias(alias);
        if (player == null) {
            throw new ResourceNotFoundException("Player");
        }
        return new PlayerDTO(player.getAlias(), player.getRanking());
    }

    //Retrieves all the players.
    public PlayersDTO getAllPlayers() {
        var players = playerRepository.findAll().stream()
                                                                .map(player -> new PlayerDTO(player.getAlias(), player.getRanking()))
                                                                .collect(Collectors.toList());
        return new PlayersDTO(players);
    }

    //Retrieves all players based on their rankings
    public PlayersDTO getAllPlayersByRanking(int ranking) {
        if (ranking < 1 || ranking > 9) {
            throw new InvalidInputException("ranking");
        }
        var players = playerRepository.findPlayersByRanking(ranking).stream()
                                                                .map(player -> new PlayerDTO(player.getAlias(), player.getRanking()))
                                                                .collect(Collectors.toList());
        return new PlayersDTO(players);
    }

    //adds a new player if provided fields aren't empty
    public PlayerDTO insertNewPlayer(String alias, int ranking) {
        if (alias == null) {
            throw new InvalidInputException("alias");
        }
        if (ranking < 1 || ranking > 9) {
            throw new InvalidInputException("ranking");
        }

        Player newPlayer = new Player(alias, ranking);
        playerRepository.save(newPlayer);
        return new PlayerDTO(alias, ranking);
    }

}