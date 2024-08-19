package ica.oose.padel.backend.services;

import ica.oose.padel.backend.persistence.Game;
import ica.oose.padel.backend.persistence.repositories.GameRepository;
import ica.oose.padel.backend.services.dto.GameDTO;
import ica.oose.padel.backend.services.dto.GamesDTO;
import ica.oose.padel.backend.services.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.stream.Collectors;

public class GameService {

    private GameRepository gameRepository;

    @Autowired
    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    //Saves game to the database, and returns corresponding GameDTO
    public GameDTO insertNewGame(Date date, String clubName, String field, String madeBy, String result) {
        if (date == null) {
            throw new InvalidInputException("date");
        }
        if (clubName == null) {
            throw new InvalidInputException("clubName");
        }
        if (field == null) {
            throw new InvalidInputException("field");
        }
        if (madeBy == null) {
            throw new InvalidInputException("madeBy");
        }

        Game newGame = new Game(date, clubName, field, madeBy, result);
        gameRepository.save(newGame);
        return new GameDTO(date, clubName, field, madeBy, result);
    }

    public GamesDTO getAllGamesByPlayer(String alias){
        var games = gameRepository.findGamesByMadeBy(alias);
        return new GamesDTO(games.stream()
                            .map(game -> new GameDTO(game.getDate(), game.getClubName(), game.getField(), game.getMadeBy(), game.getResult()))
                            .collect(Collectors.toList())
        );
    }
}
