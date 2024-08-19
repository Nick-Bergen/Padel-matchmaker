package ica.oose.padel.backend.services.dto;

import java.util.Date;

public class InviteDTO {

    private String gameDate;
    private String gameClubName;
    private String gameField;
    private String invitee;
    private boolean accepted;

    public InviteDTO(){}

    public InviteDTO(String gameDate, String gameClubName, String gameField, String invitee, boolean accepted) {
        this.gameDate = gameDate;
        this.gameClubName = gameClubName;
        this.gameField = gameField;
        this.invitee = invitee;
        this.accepted = accepted;
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

    public String getInvitee() {
        return invitee;
    }

    public void setInvitee(String invitee) {
        this.invitee = invitee;
    }

    public boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
