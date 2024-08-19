package ica.oose.padel.backend.persistence;

import javax.persistence.*;
import java.util.List;

@Entity(name = "club")
public class Club {

    @Id
    private String name;

    private String location;

    private String openingHours;



    public Club() {}

    public Club(String name, String location, String openingHours) {
        this.name = name;
        this.location = location;
        this.openingHours = openingHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }


}
