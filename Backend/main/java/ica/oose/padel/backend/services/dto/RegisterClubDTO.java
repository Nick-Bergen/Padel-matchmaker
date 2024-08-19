package ica.oose.padel.backend.services.dto;

public class RegisterClubDTO {
    private String name;
    private String location;
    private String openingHours;

    //Constructor has to be present for the rest service
    public RegisterClubDTO() {
    }

    public RegisterClubDTO(String name, String location, String openingHours) {
        this.name = name;
        this.location = location;
        this.openingHours = openingHours;
    }

    public RegisterClubDTO(String name) {
        this.name = name;
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
