package ica.oose.padel.backend.persistence;

import javax.persistence.*;
import java.util.List;

@Entity(name = "field")
@IdClass(FieldId.class)
public class Field {

    @Id
    private String name;

    @Id
    private String clubName;



    public Field() {}

    public Field(String name, String clubName) {
        this.name = name;
        this.clubName = clubName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClub() {
        return clubName;
    }

    public void setClub(Club club) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

}