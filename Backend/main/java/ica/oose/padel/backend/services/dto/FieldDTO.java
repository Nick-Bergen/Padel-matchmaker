package ica.oose.padel.backend.services.dto;

public class FieldDTO {
    private String name;
    private String clubName;

    //Constructor has to be present for the rest service
    public FieldDTO() {
    }

    public FieldDTO(String name, String clubName) {
        this.name = name;
        this.clubName = clubName;
    }

    public FieldDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
