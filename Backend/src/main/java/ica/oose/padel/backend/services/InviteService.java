package ica.oose.padel.backend.services;

import ica.oose.padel.backend.persistence.Invite;
import ica.oose.padel.backend.persistence.Player;
import ica.oose.padel.backend.persistence.repositories.InviteRepository;
import ica.oose.padel.backend.persistence.repositories.PlayerRepository;
import ica.oose.padel.backend.services.dto.*;
import ica.oose.padel.backend.services.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class InviteService {

    private InviteRepository inviteRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public void SetInviteRepository(InviteRepository inviteRepository){this.inviteRepository = inviteRepository;}

    @Autowired
    public void SetPlayerRepository(PlayerRepository playerRepository){this.playerRepository = playerRepository;}

    //Saves the invites to the database, and returns the corresponding InvitesDTO
    public InvitesDTO invitePlayers(PrepareInviteDTO prepareInviteDTO){
        List<PlayerDTO> players = prepareInviteDTO.getPlayers();
        ArrayList<PlayerDTO> playersArray = new ArrayList<>();
        ArrayList<InviteDTO> invites = new ArrayList<>();
        final int INVITE_AMOUNT = 3;

        if(prepareInviteDTO.getGameDate() == null){
            throw new InvalidInputException("prepareinviteDTO gameDate null");
        }
        else if(prepareInviteDTO.getGameClubName() == null){
            throw new InvalidInputException("prepareinviteDTO getGameClubName null");
        }
        else if(prepareInviteDTO.getGameField() == null){
            throw new InvalidInputException("prepareinviteDTO getGameField null");
        }

        int playerCount = 0;
        for(PlayerDTO p : players){
            Player player = playerRepository.findPlayerByAlias(p.getAlias());
            PlayerDTO playerDTO = new PlayerDTO(player.getAlias(), player.getRanking());
            playersArray.add(playerDTO);
            playerCount++;
        }

        if(playerCount != INVITE_AMOUNT){
            throw new InvalidInputException("invite amount");
        }

        for(PlayerDTO p : playersArray) {
            Invite invite = new Invite(prepareInviteDTO.getGameDate(), prepareInviteDTO.getGameClubName(), prepareInviteDTO.getGameField(), p.getAlias(), false);
            inviteRepository.save(invite);
            InviteDTO inviteDTO = new InviteDTO(prepareInviteDTO.getGameDate(), prepareInviteDTO.getGameClubName(), prepareInviteDTO.getGameField(), p.getAlias(), false);
            invites.add(inviteDTO);
        }

        return new InvitesDTO(invites);
    }

}
