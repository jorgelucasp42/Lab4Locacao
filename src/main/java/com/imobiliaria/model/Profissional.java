package com.imobiliaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PROFISSIONAIS")
public class Profissional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45)
    private String nome;

    @Column(length = 45)
    private String profissao;

    @Column(length = 12)
    private String telefone1;

    @Column(length = 12)
    private String telefone2;

    private Double valorHora;

    @Column(length = 1000)
    private String obs;
}
