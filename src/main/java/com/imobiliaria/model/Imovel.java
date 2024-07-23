package com.imobiliaria.model;

import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Data
@Entity
@Table(name = "IMOVEIS")
public class Imovel implements EntidadeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_imovel", nullable = false)
    private TipoImovel tipoImovel;

    @ManyToOne
    @JoinColumn(name = "id_proprietario", nullable = false)
    private Cliente proprietario;

    @Column(length = 200, nullable = false)
    private String logradouro;

    @Column(length = 45, nullable = false)
    private String bairro;

    @Column(length = 10, nullable = false)
    private String cep;

    private Integer metragem;

    private Byte dormitorios;

    private Byte banheiros;

    private Byte suites;

    private Byte vagasGaragem;

    @Column(name = "valor_aluguel_sugerido", precision = 10, scale = 2)
    private Double valorAluguelSugerido;

    @Column(length = 1000)
    private String obs;

    @OneToMany(mappedBy = "imovel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Locacao> locacoes = new ArrayList<>();

    @OneToMany(mappedBy = "imovel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicoImovel> servicosImovel = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Imovel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("logradouro='" + logradouro + "'")
                .add("valorAluguelSugerido=" + valorAluguelSugerido)
                // Adicione os outros atributos aqui
                .toString();
    }
}
