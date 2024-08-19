package ica.oose.padel.backend.persistence;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "game")
@IdClass(GameId.class)
public class Game {

    @Id
    private Date date;

    @Id
    private String clubName;

    @Id
    private String field;

    private String madeBy;
    private String result;

    public Game() {}

    public Game(Date date, String clubName, String field, String madeBy, String result) {
        this.date = date;
        this.clubName = clubName;
        this.field = field;
        this.madeBy = madeBy;
        this.result = result;
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

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}