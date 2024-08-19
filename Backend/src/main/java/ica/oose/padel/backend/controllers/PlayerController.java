package ica.oose.padel.backend.controllers;

import ica.oose.padel.backend.services.PlayerService;
import ica.oose.padel.backend.services.dto.LoginDTO;
import ica.oose.padel.backend.services.dto.RegisterPlayerDTO;
import ica.oose.padel.backend.services.dto.PlayerDTO;
import ica.oose.padel.backend.services.dto.PlayersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController{

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    //POST: Login endpoint for a player
    @PostMapping(path = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PlayerDTO loginPlayer(@RequestBody LoginDTO loginDTO) {
        return playerService.getPlayerByAlias(loginDTO.getAlias());
    }
    //POST: Register endpoint for a player to register at
    @PostMapping(path = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PlayerDTO loginPlayer(@RequestBody RegisterPlayerDTO registerPlayerDTO) {
        return playerService.insertNewPlayer(registerPlayerDTO.getAlias(), registerPlayerDTO.getRanking());
    }
    //GET: Retrieves all the players
    @GetMapping(path = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PlayersDTO getAllPlayers() {
        return playerService.getAllPlayers();
    }
    //GET: Retrieves all the players based on the ranking provided
    @GetMapping(path = "ranking", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PlayersDTO getAllPlayersByRanking(@RequestParam int ranking) {
        return playerService.getAllPlayersByRanking(ranking);
    }
}
