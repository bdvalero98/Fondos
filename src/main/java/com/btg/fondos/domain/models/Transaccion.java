package com.btg.fondos.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "transaccion")
public class Transaccion {

    private Integer id;
    private Integer fondoId;
    private String tipo;
    private Double monto;
    private LocalDateTime fecha;
}
