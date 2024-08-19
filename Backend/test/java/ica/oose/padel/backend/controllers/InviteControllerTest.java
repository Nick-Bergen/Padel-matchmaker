package ica.oose.padel.backend.controllers;

import ica.oose.padel.backend.services.FieldService;
import ica.oose.padel.backend.services.InviteService;

import ica.oose.padel.backend.services.PlayerService;
import ica.oose.padel.backend.services.dto.FieldDTO;
import ica.oose.padel.backend.services.dto.InviteDTO;
import ica.oose.padel.backend.services.dto.PlayerDTO;
import ica.oose.padel.backend.services.dto.PrepareInviteDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class InviteControllerTest {

    InviteController sut;
    @Mock
    InviteService inviteServiceMock;

    @BeforeEach
    void setup() {
        sut = new InviteController();
        sut.setInviteService(inviteServiceMock);
    }

    @Test
    public void testInvitePlayersCallsInvitePlayersWithCorrectPrepareInviteDTO(){
        //Arrange
        String gamedate = "2022-05-24 02:00:00";
        String gameClubName = "testClub";
        String gameField = "Veld 1";

        List<PlayerDTO> players = new ArrayList<>();
        players.add(new PlayerDTO("test", 4));
        players.add(new PlayerDTO("test1", 6));
        players.add(new PlayerDTO("test2", 8));
        PrepareInviteDTO prepareInviteDTO = new PrepareInviteDTO(gamedate, gameClubName, gameField, players);

        //Act
        sut.invitePlayers(prepareInviteDTO);

        //Assert
        Mockito.verify(inviteServiceMock).invitePlayers(prepareInviteDTO);
    }

}
