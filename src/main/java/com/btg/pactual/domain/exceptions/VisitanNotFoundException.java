package com.btg.pactual.domain.exceptions;

public class VisitanNotFoundException extends RuntimeException {

    public VisitanNotFoundException(String mensaje) {
        super(mensaje);
    }
}
