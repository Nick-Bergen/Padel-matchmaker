package ica.oose.padel.backend.services;

import ica.oose.padel.backend.persistence.Player;
import ica.oose.padel.backend.persistence.repositories.PlayerRepository;
import ica.oose.padel.backend.services.dto.PlayerDTO;
import ica.oose.padel.backend.services.dto.PlayersDTO;
import ica.oose.padel.backend.services.exceptions.InvalidInputException;
import ica.oose.padel.backend.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlayerServiceTest {

    PlayerService sut;
    @Mock
    PlayerRepository playerRepositoryMock;

    @BeforeEach
    void setUp() {
        sut = new PlayerService();
        sut.setPlayerRepository(playerRepositoryMock);

        //Insert test data
        Player newPlayer = new Player("Test Player", 5);
        List<Player> players = new ArrayList<>();
        players.add(newPlayer);
        Mockito.when(playerRepositoryMock.findPlayerByAlias(newPlayer.getAlias())).thenReturn(newPlayer);
        Mockito.when(playerRepositoryMock.findPlayersByRanking(newPlayer.getRanking())).thenReturn(players);
        Mockito.when(playerRepositoryMock.findAll()).thenReturn(players);
    }

    @Test
    public void testGetPlayerByAliasSuccess() {
        //Arrange
        String playerName = "Test Player";

        //Act
        sut.getPlayerByAlias(playerName);

        //Assert
        Mockito.verify(playerRepositoryMock).findPlayerByAlias(playerName);
    }

    @Test
    public void testGetPlayerByAliasThrowsExceptionOnPlayerNotFound() {
        //Arrange
        String playerName = "Fake Player";

        //Act
        assertThrows(ResourceNotFoundException.class,
                () -> sut.getPlayerByAlias(playerName));
    }

    @Test
    public void testGetAllPlayersSuccess() {
        //Arrange
        PlayerDTO player = new PlayerDTO("Test Player", 5);
        List<PlayerDTO> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(player);

        //Act
        PlayersDTO playersDTO = sut.getAllPlayers();
        List<PlayerDTO> actualPlayers = playersDTO.getPlayers();

        //Assert
        assert actualPlayers != null;
        for (int i = 0; i < expectedPlayers.size(); i++) {
            PlayerDTO expectedPlayer = expectedPlayers.get(i);
            PlayerDTO actualPlayer = actualPlayers.get(i);

            assertEquals(expectedPlayer.getAlias(), actualPlayer.getAlias());
            assertEquals(expectedPlayer.getRanking(), actualPlayer.getRanking());
        }
    }

    @Test
    public void testGetAllPlayersByRankingSuccess() {
        //Arrange
        PlayerDTO player = new PlayerDTO("Test Player", 5);
        List<PlayerDTO> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(player);

        //Act
        PlayersDTO playersDTO = sut.getAllPlayersByRanking(player.getRanking());
        List<PlayerDTO> actualPlayers = playersDTO.getPlayers();

        //Assert
        assert actualPlayers != null;
        for (int i = 0; i < expectedPlayers.size(); i++) {
            PlayerDTO expectedPlayer = expectedPlayers.get(i);
            PlayerDTO actualPlayer = actualPlayers.get(i);

            assertEquals(expectedPlayer.getAlias(), actualPlayer.getAlias());
            assertEquals(expectedPlayer.getRanking(), actualPlayer.getRanking());
        }
    }

    @Test
    public void testGetAllPlayersByRankingExceptionOnOutOfBoundsRanking() {
        //Act & Assert
        assertThrows(InvalidInputException.class,
                () -> sut.getAllPlayersByRanking(0));
    }

    @Test
    public void testInsertNewPlayerReturnsDTOOnSuccess() {
        //Arrange
        String alias = "New Player";
        int ranking = 5;
        PlayerDTO expectedPlayerDTO = new PlayerDTO(alias, ranking);
        //Act
        PlayerDTO actualPlayerDTO = sut.insertNewPlayer(alias, ranking);

        //Assert
        assertEquals(expectedPlayerDTO.getAlias(), actualPlayerDTO.getAlias());
        assertEquals(expectedPlayerDTO.getRanking(), actualPlayerDTO.getRanking());
    }

    @Test
    public void testInsertNewPlayerSavesPlayerOnSuccess() {
        //Arrange
        String alias = "New Player";
        int ranking = 5;
        //Act
        sut.insertNewPlayer(alias, ranking);

        //Assert
        Mockito.verify(playerRepositoryMock).save(Mockito.any(Player.class));
    }

    @Test
    public void testInsertNewPlayerExceptionOnInvalidAlias() {
        //Arrange
        String alias = null;
        int ranking = 5;
        //Act & Assert
        assertThrows(InvalidInputException.class,
                () -> sut.insertNewPlayer(alias, ranking));
    }

    @Test
    public void testInsertNewPlayerExceptionOnInvalidRanking() {
        //Arrange
        String alias = "New Player";
        int ranking = 0;
        //Act & Assert
        assertThrows(InvalidInputException.class,
                () -> sut.insertNewPlayer(alias, ranking));
    }
}