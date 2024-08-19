package ica.oose.padel.backend.controllers;

import ica.oose.padel.backend.services.GameService;
import ica.oose.padel.backend.services.dto.GameDTO;
import ica.oose.padel.backend.services.dto.PlayerDTO;
import ica.oose.padel.backend.services.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GameControllerTest {
    GameController sut;
    @Mock
    GameService gameServiceMock;

    @BeforeEach
    void setUp() {
        sut = new GameController();
        sut.setGameService(gameServiceMock);
    }

    @Test
    public void testAddGameCallsInsertNewGame() {
        //Arrange
        Date date = new Date();
        String clubName = "testClub";
        String fieldDTO = "Veld 3";
        String madeBy = "Zoey59";
        String result = "";
        GameDTO gameDTO = new GameDTO(date,clubName,fieldDTO,madeBy,result);

        //Act
        sut.createGame(gameDTO);

        //Assert
        Mockito.verify(gameServiceMock).insertNewGame(date,clubName,fieldDTO,madeBy,result);
    }

    @Test
    public void testGetAllGamesByPlayer() {
        //Arrange
        String testPlayer = "Test player";

        //Act
        sut.getAllGamesByPlayer(testPlayer);

        //Assert
        Mockito.verify(gameServiceMock).getAllGamesByPlayer(testPlayer);
    }
}