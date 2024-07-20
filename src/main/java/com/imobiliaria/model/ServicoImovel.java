package com.imobiliaria.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class ServicoImovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_profissional", nullable = false)
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "id_imovel", nullable = false)
    private Imovel imovel;

    @Column(nullable = false)
    private Date dataServico;

    @Column(precision = 10, scale = 2, nullable = false)
    private Double valorTotal;

    @Column(columnDefinition = "TEXT")
    private String obs;
}
