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
@Document(collection = "inscripcion")
public class Inscripcion {

    @Id
    private String id;

    @DBRef
    private Producto producto;

    @DBRef
    private Cliente cliente;
}
