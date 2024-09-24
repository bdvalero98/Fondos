package com.btg.pactual.controllers;

import com.btg.pactual.domain.exceptions.ClienteNotFoundException;
import com.btg.pactual.domain.models.Cliente;
import com.btg.pactual.domain.models.Inscripcion;
import com.btg.pactual.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Crear un nuevo cliente",
            description = "Crea un nuevo cliente con la informacion proporcionada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, verifique los datos enviados")
    })
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        try {
            Cliente clienteNuevo = clienteService.crearCliente(cliente);
            return new ResponseEntity<>(clienteNuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Obtener un cliente por ID",
            description = "Devuelve los datos de un cliente especifico segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable String id) {
        try {
            Cliente cliente = clienteService.obtenerClientePorId(id);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (ClienteNotFoundException clienteNotFoundException) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener todos los clientes",
            description = "Devuelve una lista de todos los clientes registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes Obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar un cliente",
            description = "Actualiza la informacion de un cliente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable String id,
                                                     @RequestBody Cliente clienteActualizado) {
        try {
            Cliente cliente = clienteService.actualizarCliente(id, clienteActualizado);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (ClienteNotFoundException clienteNotFoundException) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente segun su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String id) {
        try {
            clienteService.eliminarCliente(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ClienteNotFoundException clienteNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/inscripciones")
    public ResponseEntity<List<Inscripcion>> obtenerHistorialInscripciones(@PathVariable String id) {
        List<Inscripcion> inscripciones = clienteService.obtenerHistorialInscripciones(id);
        return ResponseEntity.ok(inscripciones);
    }
}
