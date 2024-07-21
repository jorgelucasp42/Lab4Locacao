package com.imobiliaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TIPO_IMOVEL")
public class TipoImovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 256)
    private String descricao;
}
