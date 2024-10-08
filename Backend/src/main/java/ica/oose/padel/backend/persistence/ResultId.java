package ica.oose.padel.backend.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ResultId implements Serializable {

    private String playerAlias;
    private Date date;
    private String clubName;
    private String field;

    public ResultId() {}

    public ResultId(String playerAlias, Date date, String clubName, String field) {
        this.playerAlias = playerAlias;
        this.date = date;
        this.clubName = clubName;
        this.field = field;
    }

    //This method checks to make sure that the obj argument is not null and that it references an instance of the same type as this object.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultId resultId = (ResultId) o;
        return Objects.equals(playerAlias, resultId.playerAlias) && Objects.equals(date, resultId.date) && Objects.equals(clubName, resultId.clubName) && Objects.equals(field, resultId.field);
    }

    //This method returns an integer value, generated by a hashing algorithm. Objects that are equal (according to their equals()) must return the same hash code.
    @Override
    public int hashCode() {
        return Objects.hash(playerAlias, date, clubName, field);
    }

}
