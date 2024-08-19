package ica.oose.padel.backend.services.dto;

import java.util.List;

public class GamesDTO {
    private List<GameDTO> games;

    //Constructor has to be present for the rest service
    public GamesDTO() {
    }

    public GamesDTO(List<GameDTO> games) {
        this.games = games;
    }

    public List<GameDTO> getGames() {
        return games;
    }

    public void setGames(List<GameDTO> games) {
        this.games = games;
    }
}
