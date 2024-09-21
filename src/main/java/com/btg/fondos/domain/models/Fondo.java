package com.btg.fondos.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "fondos")
public class Fondo {

    @Id
    private String id;
    private String nombre;
    private Double montoMinimoVinculacion;
    private String categoria;
}
