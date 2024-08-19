package ica.oose.padel.backend.services;

import ica.oose.padel.backend.persistence.Club;
import ica.oose.padel.backend.persistence.repositories.ClubRepository;
import ica.oose.padel.backend.services.dto.ClubDTO;
import ica.oose.padel.backend.services.exceptions.DuplicateEntityException;
import ica.oose.padel.backend.services.exceptions.InvalidInputException;
import ica.oose.padel.backend.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClubServiceTest {

    ClubService sut;
    @Mock
    ClubRepository clubRepositoryMock;

    @BeforeEach
    public void setUp() {
        sut = new ClubService();
        sut.setClubRepository(clubRepositoryMock);
    }

    @Test
    public void testGetClubByNameSuccess() {
        //Arrange
        Club testClub = new Club("Test Club", "Nijmegen", "09:00 - 20:00");
        Mockito.when(clubRepositoryMock.findClubByName(testClub.getName())).thenReturn(testClub);
        clubRepositoryMock.save(testClub);
        String name = "Test Club";

        //Act
        ClubDTO actualClubDTO = sut.getClubByName(name);

        //Assert
        assertEquals(name, actualClubDTO.getName());
    }

    @Test
    public void testGetClubByNameExceptionOnInvalidClub() {
        //Arrange
        Club testClub = new Club("Test Club", "Nijmegen", "09:00 - 20:00");
        Mockito.when(clubRepositoryMock.findClubByName(testClub.getName())).thenReturn(testClub);
        clubRepositoryMock.save(testClub);
        String name = "Fake Club";
        //Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> sut.getClubByName(name));
    }

    @Test
    public void testInsertNewClubReturnsClubDTOOnSuccess() {
        //Arrange
        String name = "Test Club";
        String location = "Test Location";
        String openingHours = "Test OpeningHours";
        ClubDTO expectedClubDTO = new ClubDTO(name);

        //Act
        ClubDTO actualClubDTO = sut.insertNewClub(name, location, openingHours);

        //Assert
        assertEquals(expectedClubDTO.getName(), actualClubDTO.getName());
    }

    @Test
    public void testInsertNewClubSavesClubOnSuccess() {
        //Arrange
        String name = "Test Club";
        String location = "Test Location";
        String openingHours = "Test OpeningHours";

        //Act
        sut.insertNewClub(name, location, openingHours);

        //Assert
        Mockito.verify(clubRepositoryMock).save(Mockito.any(Club.class));
    }

    @Test
    public void testInsertNewClubExceptionOnInvalidName() {
        //Arrange
        String location = "Test Location";
        String openingHours = "Test OpeningHours";

        //Act & Assert
        assertThrows(InvalidInputException.class,
                () -> sut.insertNewClub(null, location, openingHours));
    }

    @Test
    public void testInsertNewClubExceptionOnInvalidPlace() {
        //Arrange
        String name = "Test Club";
        String openingHours = "Test OpeningHours";

        //Act & Assert
        assertThrows(InvalidInputException.class,
                () -> sut.insertNewClub(name, null, openingHours));
    }

    @Test
    public void testInsertNewClubExceptionOnInvalidTimeslots() {
        //Arrange
        String name = "Test Club";
        String location = "Test Location";

        //Act & Assert
        assertThrows(InvalidInputException.class,
                () -> sut.insertNewClub(name, location, null));
    }

    @Test
    public void testInsertNewClubExceptionOnExistingClub() {
        //Arrange
        Club testClub = new Club("Test Club", "Test Location", "Test OpeningHours");
        Mockito.when(clubRepositoryMock.findClubByName(testClub.getName())).thenReturn(testClub);
        clubRepositoryMock.save(testClub);
        String name = "Test Club";
        String location = "Test Location";
        String openingHours = "Test OpeningHours";

        //Act & Assert
        assertThrows(DuplicateEntityException.class,       //Exception class to be replaced with custom exception
                () -> sut.insertNewClub(name, location, openingHours));
    }
}