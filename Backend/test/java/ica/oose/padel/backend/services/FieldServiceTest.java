package ica.oose.padel.backend.services;

import ica.oose.padel.backend.persistence.Club;
import ica.oose.padel.backend.persistence.Field;
import ica.oose.padel.backend.persistence.repositories.ClubRepository;
import ica.oose.padel.backend.persistence.repositories.FieldRepository;
import ica.oose.padel.backend.services.dto.FieldDTO;

import ica.oose.padel.backend.services.dto.FieldsDTO;
import ica.oose.padel.backend.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
class FieldServiceTest {

    FieldService sut;
    @Mock
    ClubRepository clubRepositoryMock;
    @Mock
    FieldRepository fieldRepositoryMock;

    @BeforeEach
    public void Setup() {
        sut = new FieldService();
        sut.setClubRepository(clubRepositoryMock);
        sut.setFieldRepository(fieldRepositoryMock);

        //Create a testing club to link the fields to
        Club testClub = new Club("Test Club", "Nijmegen", "09:00 - 20:00");
        Mockito.when(clubRepositoryMock.findClubByName(testClub.getName())).thenReturn(testClub);
        clubRepositoryMock.save(testClub);
    }

    //InsertNewFields

    @Test
    public void testinsertNewFieldReturnsFieldDTOOnSuccess() {
        //Arrange
        Club testClub = new Club("Test Club", "Nijmegen", "09:00 - 20:00");
        String name = "Test Field2";
        String club = "Test Club";
        FieldDTO expectedFieldDTO = new FieldDTO(name, club);
        //Add a test baan to the club
        Field field = new Field("Test Field", testClub.getName());
        fieldRepositoryMock.save(field);

        //Act
        FieldDTO actualFieldDTO = sut.insertNewField(name, club);

        //Assert
        assertEquals(expectedFieldDTO.getName(), actualFieldDTO.getName());
        assertEquals(expectedFieldDTO.getClubName(), actualFieldDTO.getClubName());
    }

    @Test
    public void testInsertNewFieldSavesFieldOnSuccess() {
        //Arrange
        String name = "Test Field2";
        String club = "Test Club";

        //Act
        sut.insertNewField(name, club);

        //Assert
        Mockito.verify(fieldRepositoryMock).save(Mockito.any(Field.class));
    }

    @Test
    public void testInsertNewFieldExceptionOnInvalidClubname(){
        //Arrange
        String name = "Test Field2";

        //Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> sut.insertNewField(name, null));
    }

    //getAllFieldsFromClub

    @Test
    public void testGetAllFieldsFromClubSucces() {
        //Arrange
        Club testClub = new Club("Test Club", "Nijmegen", "09:00 - 20:00");
            //Mocking
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("Test Field", testClub.getName()));
        fields.add(new Field("Test Field2", testClub.getName()));
        fields.add(new Field("Test Field2", new Club("Test Club2", "Test Location", "Test OpeningHours").getName()));
        Mockito.when(fieldRepositoryMock.findFieldsByClubName(testClub.getName())).thenReturn(fields);
            //Expected result
        List<FieldDTO> expectedFields =
                fields.stream().map(field -> new FieldDTO(
                        field.getName(),
                        field.getClubName())
                        ).collect(Collectors.toList());
        FieldsDTO expectedFieldsDTO = new FieldsDTO(expectedFields);

        //Act
        FieldsDTO actualFieldsDTO = sut.getAllFieldsFromClub(testClub.getName());

        //Assert
        assert actualFieldsDTO != null;
        for (int i = 0; i < expectedFieldsDTO.getFields().size(); i++) {
            var expectedField = expectedFieldsDTO.getFields().get(i);
            var actualField = actualFieldsDTO.getFields().get(i);

            assertEquals(expectedField.getName(), actualField.getName());
            assertEquals(expectedField.getClubName(), actualField.getClubName());
        }
    }

    @Test
    public void testGetAllFieldsFromClubExceptionOnInvalidClubname(){
        //Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> sut.getAllFieldsFromClub(null));
    }
}