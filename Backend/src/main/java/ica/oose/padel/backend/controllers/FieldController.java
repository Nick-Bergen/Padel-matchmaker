package ica.oose.padel.backend.controllers;

import ica.oose.padel.backend.services.FieldService;
import ica.oose.padel.backend.services.dto.FieldDTO;
import ica.oose.padel.backend.services.dto.FieldsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/field")
public class FieldController {

    private FieldService fieldService;

    @Autowired
    public void setFieldService(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FieldDTO addField(@RequestBody FieldDTO fieldDTO) {
        return fieldService.insertNewField(fieldDTO.getName(), fieldDTO.getClubName());
    }

    @GetMapping(path = "club", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public FieldsDTO getAllFieldsByClub(@RequestParam String club) {
        return fieldService.getAllFieldsFromClub(club);
    }
}
