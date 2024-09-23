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
@Document(collection = "producto")
public class Producto {

    @Id
    private String idProducto;
    private String tipoProducto;

    @DBRef
    private Sucursal sucursal;

    @DBRef
    private List<Inscripcion> inscripciones;

    @DBRef
    private List<Disponibilidad> disponibilidades;
}
