package com.btg.pactual.domain.exceptions;

public class SucursalNotFoundException extends RuntimeException {

    public SucursalNotFoundException(String mensaje) {
        super(mensaje);
    }
}
