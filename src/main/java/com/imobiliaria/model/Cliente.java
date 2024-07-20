package com.imobiliaria.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String nome;

    @Column(length = 15, nullable = false, unique = true)
    private String cpf;

    @Column(length = 12, nullable = false)
    private String telefone;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private Date dtNascimento;

    @OneToMany(mappedBy = "inquilino")
    private List<Locacao> locacoes;
}
