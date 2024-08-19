package ica.oose.padel.backend.services.dto;

import java.util.List;

public class FieldsDTO {
    private List<FieldDTO> fields;

    //Constructor has to be present for the rest service
    public FieldsDTO() {
    }

    public FieldsDTO(List<FieldDTO> fields) {
        this.fields = fields;
    }

    public List<FieldDTO> getFields() {
        return fields;
    }

    public void setFields(List<FieldDTO> fields) {
        this.fields = fields;
    }
}
