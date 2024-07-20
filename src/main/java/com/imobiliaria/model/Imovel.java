package com.imobiliaria.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer idProprietario;

    @ManyToOne
    @JoinColumn(name = "id_tipo_imovel", nullable = false)
    private TipoImovel tipoImovel;

    @Column(length = 200, nullable = false)
    private String logradouro;

    @Column(length = 45, nullable = false)
    private String bairro;

    @Column(length = 10, nullable = false)
    private String cep;

    @Column(nullable = false)
    private Integer metragem;

    @Column(nullable = false)
    private Byte dormitorios;

    @Column(nullable = false)
    private Byte banheiros;

    @Column(nullable = false)
    private Byte suites;

    @Column(nullable = false)
    private Byte vagasGaragem;

    @Column(precision = 10, scale = 2)
    private Double valorAluguelSugerido;

    @Column(columnDefinition = "TEXT")
    private String obs;

    @OneToMany(mappedBy = "imovel")
    private List<Locacao> locacoes;
}
