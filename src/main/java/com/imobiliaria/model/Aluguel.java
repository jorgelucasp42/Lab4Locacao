package com.imobiliaria.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_locacao", nullable = false)
    private Locacao locacao;

    @Column(nullable = false)
    private Date dataVencimento;

    @Column(precision = 10, scale = 2, nullable = false)
    private Double valorPago;

    private Date dataPagamento;

    @Column(columnDefinition = "TEXT")
    private String obs;
}

