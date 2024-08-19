package ica.oose.padel.backend.controllers;

import ica.oose.padel.backend.services.dto.*;
import ica.oose.padel.backend.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(path = "new", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GameDTO createGame(@RequestBody GameDTO gameDTO) {
        return gameService.insertNewGame(gameDTO.getDate(), gameDTO.getClubName(), gameDTO.getField(), gameDTO.getMadeBy(), gameDTO.getResult());
    }

    @GetMapping(path = "player", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GamesDTO getAllGamesByPlayer(@RequestParam String alias) {
        return gameService.getAllGamesByPlayer(alias);
    }
}
