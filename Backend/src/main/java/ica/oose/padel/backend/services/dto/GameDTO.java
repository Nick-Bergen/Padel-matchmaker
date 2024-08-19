package ica.oose.padel.backend.services.dto;

import java.util.Date;

public class GameDTO {
        private Date date;
    private String clubName;
    private String field;
    private String madeBy;
    private String result;

    //Constructor has to be present for the rest service
    public GameDTO() {
    }

    public GameDTO(Date date, String clubName, String field, String madeBy, String result) {
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
