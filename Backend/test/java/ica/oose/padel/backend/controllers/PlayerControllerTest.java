package ica.oose.padel.backend.controllers;

import ica.oose.padel.backend.services.PlayerService;
import ica.oose.padel.backend.services.dto.LoginDTO;
import ica.oose.padel.backend.services.dto.RegisterPlayerDTO;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.*;

@SpringBootTest
public class PlayerControllerTest {

    PlayerController sut;
    @Mock
    PlayerService PlayerServiceMock;

    @BeforeEach
    void setUp(){
        sut = new PlayerController();
        sut.setPlayerService(PlayerServiceMock);
    }

    @Test
    public void testLoginPlayerViaLogin() {
        //Arrange
        LoginDTO loginDTO = new LoginDTO("Test Player");

        //Act
        sut.loginPlayer(loginDTO);

        //Assert
        Mockito.verify(PlayerServiceMock).getPlayerByAlias(loginDTO.getAlias());
    }

    @Test
    public void testLoginPlayerViaRegister() {
        //Arrange
        RegisterPlayerDTO registerPlayerDTO = new RegisterPlayerDTO("Test Player", 5);

        //Act
        sut.loginPlayer(registerPlayerDTO);

        //Assert
        Mockito.verify(PlayerServiceMock).insertNewPlayer(registerPlayerDTO.getAlias(), registerPlayerDTO.getRanking());
    }

    @Test
    public void testGetAllPlayers() {
        //Act
        sut.getAllPlayers();

        //Assert
        Mockito.verify(PlayerServiceMock).getAllPlayers();
    }

    @Test
    public void testGetAllPlayerByRanking() {
        //Assert
        int ranking = 5;

        //Act
        sut.getAllPlayersByRanking(ranking);

        //Assert
        Mockito.verify(PlayerServiceMock).getAllPlayersByRanking(ranking);
    }
}
