package com.btg.fondos.domain.exceptions;

public class FondoNotFoundException extends RuntimeException {
    public FondoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
