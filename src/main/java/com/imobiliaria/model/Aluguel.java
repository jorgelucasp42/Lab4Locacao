package com.imobiliaria.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ALUGUEIS")
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_locacao")
    private Locacao locacao;

    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    private Double valorPago;

    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    @Column(length = 1000)
    private String obs;
}
