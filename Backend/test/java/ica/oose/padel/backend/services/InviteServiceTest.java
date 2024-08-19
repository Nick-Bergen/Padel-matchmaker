package ica.oose.padel.backend.services;

import ica.oose.padel.backend.persistence.Invite;
import ica.oose.padel.backend.persistence.Player;
import ica.oose.padel.backend.persistence.repositories.InviteRepository;
import ica.oose.padel.backend.persistence.repositories.PlayerRepository;
import ica.oose.padel.backend.services.dto.InviteDTO;
import ica.oose.padel.backend.services.dto.InvitesDTO;
import ica.oose.padel.backend.services.dto.PlayerDTO;
import ica.oose.padel.backend.services.dto.PrepareInviteDTO;
import ica.oose.padel.backend.services.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class InviteServiceTest {

    InviteService sut;
    @Mock
    InviteRepository inviteRepositoryMock;
    @Mock
    PlayerRepository playerRepositoryMock;

    String gameDate;
    String gameClubName;
    String gameField;
    Boolean accepted;

    List<PlayerDTO> players;
    PrepareInviteDTO preparedInviteDTO;

    @BeforeEach
    public void setUp() {
        sut = new InviteService();
        sut.SetInviteRepository(inviteRepositoryMock);
        sut.SetPlayerRepository(playerRepositoryMock);

        Mockito.when(playerRepositoryMock.findPlayerByAlias("test")).thenReturn(new Player("test", 4));
        Mockito.when(playerRepositoryMock.findPlayerByAlias("test1")).thenReturn(new Player("test1", 6));
        Mockito.when(playerRepositoryMock.findPlayerByAlias("test2")).thenReturn(new Player("test2", 8));

        gameDate = "2022-05-24 02:00:00";
        gameClubName = "Tennisclub Groesbeek";
        gameField = "Veld 1";
        accepted = false;

        players = new ArrayList<>();
        players.add(new PlayerDTO("test", 4));
        players.add(new PlayerDTO("test1", 6));
        players.add(new PlayerDTO("test2", 8));
        preparedInviteDTO = new PrepareInviteDTO(gameDate,gameClubName, gameField, players);
    }

    @Test
    public void testInvitePlayersReturnsCorrectInvitesDTO(){
        //Arrange
        List<InviteDTO> expectedInviteDTOList = new ArrayList<>();
        expectedInviteDTOList.add(new InviteDTO(gameDate, gameClubName, gameField, "test", accepted));
        expectedInviteDTOList.add(new InviteDTO(gameDate, gameClubName, gameField, "test1", accepted));
        expectedInviteDTOList.add(new InviteDTO(gameDate, gameClubName, gameField, "test2", accepted));

        InvitesDTO expectedInvitesDTO = new InvitesDTO(expectedInviteDTOList);

        //Act
        InvitesDTO actualInvitesDTO = sut.invitePlayers(preparedInviteDTO);

        //Assert
        for(int i = 0; i < expectedInviteDTOList.size(); i++){
            InviteDTO expectedInviteDTO = expectedInvitesDTO.getInvites().get(i);
            InviteDTO actualInviteDTO = actualInvitesDTO.getInvites().get(i);

            assertEquals(expectedInviteDTO.getGameDate(), actualInviteDTO.getGameDate());
            assertEquals(expectedInviteDTO.getGameClubName(), actualInviteDTO.getGameClubName());
            assertEquals(expectedInviteDTO.getGameField(), actualInviteDTO.getGameField());
            assertEquals(expectedInviteDTO.getInvitee(), actualInviteDTO.getInvitee());
            assertEquals(expectedInviteDTO.getAccepted(), actualInviteDTO.getAccepted());
        }
    }

    @Test
    public void testInvitePlayersSaveFunctionIsCalled() {
        //Act
        sut.invitePlayers(preparedInviteDTO);

        //Assert
        Mockito.verify(inviteRepositoryMock, times(3)).save(Mockito.any(Invite.class));
    }

    @Test
    public void testInvitePlayersInvalidInviteAmountTooLittleThrowInputException() {
        //Arrange
        players.remove(2);

        //Act
        PrepareInviteDTO faultyPrepareInviteDTO = new PrepareInviteDTO(gameDate, gameClubName, gameField, players);

        //Assert
        assertThrows(InvalidInputException.class,
                    () -> sut.invitePlayers(faultyPrepareInviteDTO));
    }

    @Test
    public void testInvitePlayersInvalidInviteAmountTooManyThrowInputException() {
        //Arrange
        Mockito.when(playerRepositoryMock.findPlayerByAlias("test3")).thenReturn(new Player("test3", 2));
        players.add(new PlayerDTO("test3", 2));

        //Act
        PrepareInviteDTO faultyPrepareInviteDTO = new PrepareInviteDTO(gameDate, gameClubName, gameField, players);

        //Assert
        assertThrows(InvalidInputException.class,
                () -> sut.invitePlayers(faultyPrepareInviteDTO));
    }

    @Test
    public void testInvitePlayersGetGameDateNullThrowInputException(){
        //Arrange
        PrepareInviteDTO faultyPrepareInviteDTO = new PrepareInviteDTO(null, gameClubName, gameField, players);

        //Assert
        assertThrows(InvalidInputException.class,
                    () -> sut.invitePlayers(faultyPrepareInviteDTO));
    }

    @Test
    public void testInvitePlayersGetGameClubNameNullThrowInputException(){
        //Arrange
        PrepareInviteDTO faultyPrepareInviteDTO = new PrepareInviteDTO(gameDate, null, gameField, players);

        //Assert
        assertThrows(InvalidInputException.class,
                    () -> sut.invitePlayers(faultyPrepareInviteDTO));
    }

    @Test
    public void testInvitePlayersGetGameFieldNullThrowInputException(){
        //Arrange
        PrepareInviteDTO faultyPrepareInviteDTO = new PrepareInviteDTO(gameDate, gameClubName, null, players);

        //Assert
        assertThrows(InvalidInputException.class,
                    () -> sut.invitePlayers(faultyPrepareInviteDTO));
    }
}
