package ica.oose.padel.backend.services.dto;

public class ClubDTO {

    private String name;
    private String location;
    private String openingHours;

    public ClubDTO(String name) {
        this.name = name;
    }

    public ClubDTO(String name, String location, String openingHours) {
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