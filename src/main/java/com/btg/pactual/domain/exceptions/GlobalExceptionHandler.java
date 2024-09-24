package com.btg.pactual.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(resourceNotFoundException.getMessage());
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<String> handleClienteNotFoundException(ClienteNotFoundException clienteNotFoundException) {
        return new ResponseEntity<>(clienteNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InscripcionNotFoundException.class)
    public ResponseEntity<String> handleInscripcionNotFoundException(
            InscripcionNotFoundException inscripcionNotFoundException) {
        return new ResponseEntity<>(inscripcionNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransaccionNotFoundException.class)
    public ResponseEntity<String> handleTransaccionNotFoundException(
            TransaccionNotFoundException transaccionNotFoundException) {
        return new ResponseEntity<>(transaccionNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VisitanNotFoundException.class)
    public ResponseEntity<String> handleVisitanNotFoundException(
            VisitanNotFoundException visitanNotFoundException) {
        return new ResponseEntity<>(visitanNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SucursalNotFoundException.class)
    public ResponseEntity<String> handleSucursalNotFoundException(
            SucursalNotFoundException sucursalNotFoundException) {
        return new ResponseEntity<>(sucursalNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralError(Exception exception) {
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An Error ocurred: " + exception.getMessage());
    }
}
