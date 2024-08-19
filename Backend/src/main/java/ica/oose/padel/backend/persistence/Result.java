package ica.oose.padel.backend.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;

@Entity(name = "result")
@IdClass(ResultId.class)
public class Result {

    @Id
    private String playerAlias;

    @Id
    private Date date;

    @Id
    private String clubName;

    @Id
    private String field;

    private boolean won;

    public Result() {}

    public Result(String playerAlias, Date date, String clubName, String field, boolean won) {
        this.playerAlias = playerAlias;
        this.date = date;
        this.clubName = clubName;
        this.field = field;
        this.won = won;
    }

    public String getPlayerAlias() {
        return playerAlias;
    }

    public void setPlayerAlias(String playerAlias) {
        this.playerAlias = playerAlias;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

}
