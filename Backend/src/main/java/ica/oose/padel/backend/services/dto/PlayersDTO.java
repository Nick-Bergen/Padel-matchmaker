package ica.oose.padel.backend.services.dto;

import java.util.List;

public class PlayersDTO {
    private List<PlayerDTO> players;

    //Constructor has to be present for the rest service
    public PlayersDTO() {
    }
    //constructor with list of players parameter
    public PlayersDTO(List<PlayerDTO> players) {
        this.players = players;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }
}
