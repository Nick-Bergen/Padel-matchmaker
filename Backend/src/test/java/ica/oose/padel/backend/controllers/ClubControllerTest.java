package ica.oose.padel.backend.controllers;

import ica.oose.padel.backend.services.ClubService;
import ica.oose.padel.backend.services.dto.LoginClubDTO;
import ica.oose.padel.backend.services.dto.RegisterClubDTO;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.*;

@SpringBootTest
public class ClubControllerTest {

    ClubController sut;
    @Mock
    ClubService clubServiceMock;

    @BeforeEach
    void setUp() {
        sut = new ClubController();
        sut.setClubService(clubServiceMock);
    }

    @Test
    public void testRegisterClubViaLogin(){
        //Arrange
        LoginClubDTO loginClubDTO = new LoginClubDTO("Test Club");

        //Act
        sut.registerClub(loginClubDTO);

        //Assert
        Mockito.verify(clubServiceMock).getClubByName(loginClubDTO.getName());
    }

    @Test
    public void testRegisterClubViaRegister() {
        //Arrange
        RegisterClubDTO registerClubDTO = new RegisterClubDTO("Test Club", "Test Location", "Test OpeningHours");

        //Act
        sut.registerClub(registerClubDTO);

        //Assert
        Mockito.verify(clubServiceMock).insertNewClub(registerClubDTO.getName(), registerClubDTO.getLocation(), registerClubDTO.getOpeningHours());
    }

    @Test
    public void testGetClubByName() {
        //Arrange
        String name = "Test Club";

        //Act
        sut.getClubByName(name);

        //Assert
        Mockito.verify(clubServiceMock).getClubByName(name);
    }

    @Test
    public void testGetAllClubs() {
        //Arrange

        //Act
        sut.getAllClubs();

        //Assert
        Mockito.verify(clubServiceMock).getAllClubs();
    }
}
