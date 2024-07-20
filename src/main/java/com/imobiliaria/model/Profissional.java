package com.imobiliaria.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String nome;

    @Column(length = 45, nullable = false)
    private String profissao;

    @Column(length = 12, nullable = false)
    private String telefone1;

    @Column(length = 12)
    private String telefone2;

    @Column(precision = 10, scale = 2)
    private Double valorHora;

    @Column(columnDefinition = "TEXT")
    private String obs;

    @OneToMany(mappedBy = "profissional")
    private List<ServicoImovel> servicos;
}
