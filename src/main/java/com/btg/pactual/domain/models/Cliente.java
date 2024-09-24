package com.btg.pactual.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cliente")
public class Cliente {

    @Id
    private String idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String ciudad;
    private Double saldo;

    @DBRef
    private List<Inscripcion> inscripciones;

    @DBRef
    private List<Visitan> visitas;

    @DBRef
    private List<Transaccion> transacciones;
}
