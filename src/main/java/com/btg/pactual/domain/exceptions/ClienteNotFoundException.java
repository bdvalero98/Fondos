package com.btg.pactual.domain.exceptions;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException(String mensaje) {
        super(mensaje);
    }
}
