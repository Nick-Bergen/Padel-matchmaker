package ica.oose.padel.backend.services.dto;

import java.util.List;

public class InvitesDTO {

    List<InviteDTO> invites;

    public InvitesDTO() {
    }

    public InvitesDTO(List<InviteDTO> invites) {this.invites = invites;}

    public List<InviteDTO> getInvites() {
        return invites;
    }

    public void setInvites(List<InviteDTO> invites) {
        this.invites = invites;
    }
}
