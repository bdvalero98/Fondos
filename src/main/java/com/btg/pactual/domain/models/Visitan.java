package com.btg.pactual.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "visitan")
public class Visitan {

    @Id
    private String id;

    @DBRef
    private Sucursal sucursal;

    @DBRef
    private Cliente cliente;

    private Date fechaVisita;
}
