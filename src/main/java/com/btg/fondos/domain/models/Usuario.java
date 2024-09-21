package com.btg.fondos.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "usuario")
public class Usuario {

    @Id
    private Integer id;
    private String nombre;
    private String email;
    private Double saldo;
    private List<Transaccion> transacciones;
}
