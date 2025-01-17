package com.btg.pactual.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "disponibilidad")
public class Disponibilidad {

    @Id
    private String idDisponibilidad;

    @DBRef
    private Producto producto;

    @DBRef
    private Sucursal sucursal;
}
