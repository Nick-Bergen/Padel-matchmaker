package ica.oose.padel.backend.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String duplicateEntityName) {
        super(duplicateEntityName + " already exists.");
    }
}
