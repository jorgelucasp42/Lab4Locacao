package com.imobiliaria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "IMOVEIS")
public class Imovel implements EntidadeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_imovel")
    private TipoImovel tipoImovel;

    @Column(length = 200)
    private String logradouro;

    @Column(length = 45)
    private String bairro;

    @Column(length = 10)
    private String cep;

    private Integer metragem;
    private Byte dormitorios;
    private Byte banheiros;
    private Byte suites;
    private Byte vagasGaragem;
    private Double valorAluguelSugerido;

    @Column(length = 1000)
    private String obs;

    @Override
    public Integer getId() {
        return id;
    }
}
