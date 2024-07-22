package com.imobiliaria.model;

import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "PROFISSIONAIS")
public class Profissional implements EntidadeBase{
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

    private Double valorHora;

    @Column(length = 1000)
    private String obs;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicoImovel> servicosImovel = new ArrayList<>();
}
