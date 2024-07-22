package com.imobiliaria.model;

import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public @Data class Cliente implements EntidadeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String nome;

    @Column(length = 12, unique = true)
    private String cpf;

    @Column(length = 12, nullable = false)
    private String telefone;

    @Column(length = 100)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date dtNascimento;

    @OneToMany(mappedBy = "inquilino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Locacao> locacoes = new ArrayList<>();

    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imovel> imoveis = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }
}
