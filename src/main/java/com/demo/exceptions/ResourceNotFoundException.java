package com.demo.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String nombreRecurso;

    private String nombreCampo;

    private long valorCampo;

    public ResourceNotFoundException(String nombreRecurso, String nombreCampo, long valorCampo) {
        super(String.format("%s not found : %s : '%s'", nombreRecurso, nombreCampo, valorCampo));
        this.nombreRecurso = nombreRecurso;
        this.nombreCampo = nombreCampo;
        this.valorCampo = valorCampo;
    }

    public ResourceNotFoundException(String nombreRecurso, String nombreCampo) {
        super(String.format("%s not found : %s", nombreRecurso, nombreCampo));
        this.nombreRecurso = nombreRecurso;
        this.nombreCampo = nombreCampo;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
