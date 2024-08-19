package ica.oose.padel.backend.services.dto;

public class LoginDTO {
    String alias;

    //Constructor has to be present for the rest service
    public LoginDTO() {
    }

    public LoginDTO(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}