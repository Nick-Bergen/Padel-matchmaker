package ica.oose.padel.backend.services.dto;

public class LoginClubDTO {
    String name;

    //Constructor has to be present for the rest service
    public LoginClubDTO() {
    }

    public LoginClubDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
