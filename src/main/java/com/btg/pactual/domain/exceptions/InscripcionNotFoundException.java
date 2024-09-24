package com.btg.pactual.domain.exceptions;

public class InscripcionNotFoundException extends RuntimeException {

    public InscripcionNotFoundException(String mensaje) {
        super(mensaje);
    }
}
