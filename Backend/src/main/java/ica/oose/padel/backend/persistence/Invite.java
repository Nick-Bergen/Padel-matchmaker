package ica.oose.padel.backend.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;

@Entity(name = "invite")
@IdClass(InviteId.class)
public class Invite {

    @Id
    private String gameDate;

    @Id
    private String gameClubName;

    @Id
    private String gameField;

    @Id
    private String invitee;

    private boolean accepted;

    public Invite() {}

    public Invite(String gameDate, String gameClubName, String gameField, String invitee, boolean accepted) {
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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

}
