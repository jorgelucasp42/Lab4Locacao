package com.imobiliaria.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "SERVICOS_IMOVEL")
public class ServicoImovel implements EntidadeBase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_profissional")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "id_imovel")
    private Imovel imovel;

    @Temporal(TemporalType.DATE)
    private Date dataServico;

    private Double valorTotal;

    @Column(length = 1000)
    private String obs;
}
