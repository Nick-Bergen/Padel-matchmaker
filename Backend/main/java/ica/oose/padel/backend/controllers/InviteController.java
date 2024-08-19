package ica.oose.padel.backend.controllers;

import ica.oose.padel.backend.services.FieldService;
import ica.oose.padel.backend.services.InviteService;
import ica.oose.padel.backend.services.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/invite")
public class InviteController {

    private InviteService inviteService;

    @Autowired
    public void setInviteService(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    //Gets called when a player invites other players
    public InvitesDTO invitePlayers(@RequestBody PrepareInviteDTO prepareInviteDTO){
        return inviteService.invitePlayers(prepareInviteDTO);
    }

}
