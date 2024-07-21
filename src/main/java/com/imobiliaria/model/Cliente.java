package com.imobiliaria.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "CLIENTES", uniqueConstraints = {@UniqueConstraint(columnNames = "cpf")})
public class Cliente implements EntidadeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45)
    private String nome;

    @Column(length = 12, unique = true)
    private String cpf;

    @Column(length = 12)
    private String telefone;

    @Column(length = 100)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date dtNascimento;

    @Override
    public Integer getId() {
        return id;
    }
}
