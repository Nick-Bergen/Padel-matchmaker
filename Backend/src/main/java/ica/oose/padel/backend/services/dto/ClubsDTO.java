package ica.oose.padel.backend.services.dto;

import java.util.List;

public class ClubsDTO {
    private List<ClubDTO> clubs;

    //Constructor has to be present for the rest service
    public ClubsDTO() {}

    public ClubsDTO(List<ClubDTO> clubs) {
        this.clubs = clubs;
    }

    public List<ClubDTO> getClubs() {
        return clubs;
    }

    public void setClubs(List<ClubDTO> clubs) {
        this.clubs = clubs;
    }
}
