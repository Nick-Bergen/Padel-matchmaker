package ica.oose.padel.backend.controllers;

import ica.oose.padel.backend.services.FieldService;
import ica.oose.padel.backend.services.dto.FieldDTO;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.*;

@SpringBootTest
public class FieldControllerTest {

    FieldController sut;
    @Mock
    FieldService fieldServiceMock;

    @BeforeEach
    void setUp() {
        sut = new FieldController();
        sut.setFieldService(fieldServiceMock);
    }

    @Test
    public void testAddField() {
        //Arrange
        String field = "Test Field";
        String club = "Test Club";
        FieldDTO fieldDTO = new FieldDTO(field, club);

        //Act
        sut.addField(fieldDTO);

        //Assert
        Mockito.verify(fieldServiceMock).insertNewField(field, club);
    }

    @Test
    public void testGetAllFieldsByClub() {
        //Arrange
        String club = "Test Club";

        //Act
        sut.getAllFieldsByClub(club);

        //Assert
        Mockito.verify(fieldServiceMock).getAllFieldsFromClub(club);
    }
}
