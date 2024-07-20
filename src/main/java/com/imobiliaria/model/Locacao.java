package com.imobiliaria.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_imovel", nullable = false)
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "id_inquilino", nullable = false)
    private Cliente inquilino;

    @Column(precision = 10, scale = 4, nullable = false)
    private Double valorAluguel;

    @Column(precision = 5, scale = 2, nullable = false)
    private Double percentualMulta;

    @Column(nullable = false)
    private Byte diaVencimento;

    @Column(nullable = false)
    private Date dataInicio;

    private Date dataFim;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(columnDefinition = "TEXT")
    private String obs;

    @OneToMany(mappedBy = "locacao")
    private List<Aluguel> aluguels;
}
