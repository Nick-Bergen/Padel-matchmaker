package ica.oose.padel.backend.services.dto;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class PrepareInviteDTO {
    private String gameDate;
    private String gameClubName;
    private String gameField;
    private List<PlayerDTO> players;

    public PrepareInviteDTO(){}

    public PrepareInviteDTO(String gameDate, String gameClubName, String gameField, List<PlayerDTO> players) {
        this.gameDate = gameDate;
        this.gameClubName = gameClubName;
        this.gameField = gameField;
        this.players = players;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getGameClubName() {
        return gameClubName;
    }

    public void setGameClubName(String gameClubName) {
        this.gameClubName = gameClubName;
    }

    public String getGameField() {
        return gameField;
    }

    public void setGameField(String gameField) {
        this.gameField = gameField;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }
}
