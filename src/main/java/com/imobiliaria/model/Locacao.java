package com.imobiliaria.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LOCACAO")
public class Locacao implements EntidadeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_imovel", nullable = false)
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "id_inquilino", nullable = false)
    private Cliente inquilino;

    @Column(nullable = true)
    private Double valorAluguel;

    @Column(nullable = true)
    private Double percentualMulta;

    @Column(nullable = false)
    private Byte diaVencimento;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date dataFim;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(length = 1000, nullable = true)
    private String obs;

    @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluguel> alugueis = new ArrayList<>();

    @Override
    public String toString() {
        return "Locacao{" +
                "id=" + id +
                ", imovel=" + (imovel != null ? imovel.getLogradouro() : null) +
                ", inquilino=" + (inquilino != null ? inquilino.getNome() : null) +
                ", valorAluguel=" + valorAluguel +
                ", percentualMulta=" + percentualMulta +
                ", diaVencimento=" + diaVencimento +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", ativo=" + ativo +
                ", obs='" + obs + '\'' +
                ", alugueis=" + alugueis.size() +
                '}';
    }
}
