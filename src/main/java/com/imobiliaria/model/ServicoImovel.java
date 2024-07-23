package com.imobiliaria.model;

import javax.persistence.*;
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
    @JoinColumn(name = "id_profissional", nullable = false)
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "id_imovel", nullable = false)
    private Imovel imovel;

    @Temporal(TemporalType.DATE)
    private Date dataServico;

    private Double valorTotal;

    @Column(length = 1000)
    private String obs;
}
