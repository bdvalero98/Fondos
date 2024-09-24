package com.btg.pactual.domain.exceptions;

public class TransaccionNotFoundException extends RuntimeException {

    public TransaccionNotFoundException(String mensaje) {
        super(mensaje);
    }
}
