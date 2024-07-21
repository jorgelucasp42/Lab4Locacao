package com.imobiliaria.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "LOCACAO")
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_imovel")
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "id_inquilino")
    private Cliente inquilino;

    private Double valorAluguel;
    private Double percentualMulta;
    private Byte diaVencimento;

    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    private Date dataFim;

    private Boolean ativo;

    @Column(length = 1000)
    private String obs;
}
