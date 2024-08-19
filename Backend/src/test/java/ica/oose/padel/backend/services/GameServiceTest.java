package ica.oose.padel.backend.services;

import ica.oose.padel.backend.persistence.Game;
import ica.oose.padel.backend.persistence.repositories.GameRepository;
import ica.oose.padel.backend.services.dto.GameDTO;
import ica.oose.padel.backend.services.dto.GamesDTO;
import ica.oose.padel.backend.services.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameServiceTest {


    GameService sut;
    @Mock
    GameRepository gameRepositoryMock;

    @BeforeEach
    public void setUp() {
        sut = new GameService();
        sut.setGameRepository(gameRepositoryMock);
    }

    @Test
    public void testAddGameSuccess() {
        //Arrange

        Date date = new Date();
        String clubName = "testGame";
        String fieldName = "veld 1";
        String madeBy = "karel";
        String result = null;
        GameDTO expectedGame = new GameDTO(date,clubName,fieldName,madeBy,result);

        //Act
        GameDTO actualGameDTO = sut.insertNewGame(date, clubName, fieldName, madeBy, result);

        //Assert
        assertEquals(expectedGame.getClubName(), actualGameDTO.getClubName());
        assertEquals(expectedGame.getDate(), actualGameDTO.getDate());
        assertEquals(expectedGame.getField(), actualGameDTO.getField());
        assertEquals(expectedGame.getMadeBy(), actualGameDTO.getMadeBy());
        assertEquals(expectedGame.getResult(), actualGameDTO.getResult());
    }
    @Test
    public void testInsertNewGameSavesClubOnSuccess() {
        //Arrange
        Date date = new Date();
        String clubName = "testGame";
        String fieldName = "veld 1";
        String madeBy = "karel";
        String result = null;

        //Act
        sut.insertNewGame(date, clubName, fieldName, madeBy, result);

        //Assert
        Mockito.verify(gameRepositoryMock).save(Mockito.any(Game.class));
    }


    @Test
    public void testAddGameWithClubNullValueThrowInputException() {
        //Arrange
        Date date = new Date();
        String clubName = null;
        String fieldDTO = "Veld 3";
        String madeBy = "";
        String result = "";

        //Act & Assert
        assertThrows(InvalidInputException.class,
                () -> sut.insertNewGame(date, clubName, fieldDTO, madeBy, result));
    }
    @Test
    public void testAddGameWithMadeByNullValueThrowInputException() {
        //Arrange
        Date date = new Date();
        String clubName = "testClub";
        String fieldDTO = "Veld 3";
        String madeBy = null;
        String result = "";

        //Act & Assert
        assertThrows(InvalidInputException.class,
                () -> sut.insertNewGame(date, clubName, fieldDTO, madeBy, result));
    }
    @Test
    public void testAddGameWithFieldNullValueThrowInputException() {
        //Arrange
        Date date = new Date();
        String clubName = "testClub";
        String fieldDTO = null;
        String madeBy = "";
        String result = "";

        //Act & Assert
        assertThrows(InvalidInputException.class,
                () -> sut.insertNewGame(date, clubName, fieldDTO, madeBy, result));
    }
    @Test
    public void testAddGameWithDateNullValueThrowInputException() {
        //Arrange
        Date date = null;
        String clubName = "testClub";
        String fieldDTO = "Veld 3";
        String madeBy = "";
        String result = "";

        //Act & Assert
        assertThrows(InvalidInputException.class,
                () -> sut.insertNewGame(date, clubName, fieldDTO, madeBy, result));
    }

    @Test
    public void testGetAllGamesByPlayerReturnsGamesDTOOnSuccess() throws ParseException {
        //Arrange

        //Set variables to use during testing
        String testPlayerAlias = "Test Player";
        //Set up a date for the test game
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String dateInString = "7-Jun-2013";
        Date testDate = formatter.parse(dateInString);
        //Create a game to add to the list
        Game game = new Game(testDate, "Test Club", "Test Field", testPlayerAlias, "1-2");
        //Create a testing List<Game> to return for "findGamesByMadeBy"
        List<Game> testGameList = new ArrayList<>();
        testGameList.add(game);
        //Set up the gameRepositoryMock requests as a Mockito
        Mockito.when(gameRepositoryMock.findGamesByMadeBy(testPlayerAlias)).thenReturn(testGameList);

        GamesDTO expectedGamesDTO = new GamesDTO(testGameList.stream()
                .map(g -> new GameDTO(g.getDate(), g.getClubName(), g.getField(), g.getMadeBy(), g.getResult()))
                .collect(Collectors.toList()));

        //Act
        GamesDTO actualGamesDTO = sut.getAllGamesByPlayer(testPlayerAlias);

        //Assert
        assert actualGamesDTO != null;
        for (int i = 0; i < expectedGamesDTO.getGames().size(); i++) {
            var expectedGame = expectedGamesDTO.getGames().get(i);
            var actualGame = actualGamesDTO.getGames().get(i);

            assertEquals(expectedGame.getDate(), actualGame.getDate());
            assertEquals(expectedGame.getClubName(), actualGame.getClubName());
            assertEquals(expectedGame.getField(), actualGame.getField());
            assertEquals(expectedGame.getMadeBy(), actualGame.getMadeBy());
            assertEquals(expectedGame.getResult(), actualGame.getResult());
        }
    }
}