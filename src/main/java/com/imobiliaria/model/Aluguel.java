package com.imobiliaria.model;

import javax.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ALUGUEIS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluguel implements EntidadeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_locacao", nullable = false)
    private Locacao locacao;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_vencimento", nullable = false)
    private Date dataVencimento;

    @Column(name = "valor_pago")
    private Double valorPago;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_pagamento")
    private Date dataPagamento;

    @Column(name = "obs", length = 1000)
    private String obs;

    @Override
    public Integer getId() {
        return id;
    }
}
