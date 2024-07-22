package com.imobiliaria.repository;

import com.imobiliaria.model.Aluguel;
import com.imobiliaria.model.Imovel;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AluguelRepository extends DAOGenerico<Aluguel> {
    public AluguelRepository(EntityManager manager) {
        super(manager);
    }

    /**
     * Recupera todos os imóveis disponíveis com valor de aluguel igual ou inferior ao valor informado.
     * @param limitePreco Valor máximo do aluguel.
     * @return Lista de imóveis disponíveis com valor de aluguel igual ou inferior ao informado.
     */
    public List<Imovel > findImoveisDisponiveisComValorInferiorOuIgual(Double limitePreco) {
        String jpql = "SELECT i FROM Imovel i WHERE i.valorAluguelSugerido <= :limitePreco " +
                "AND i.id NOT IN (SELECT a.locacao.imovel.id FROM Aluguel a WHERE a.dataPagamento IS NULL)";

        TypedQuery <Imovel> query = getManager().createQuery(jpql, Imovel.class);
        query.setParameter("limitePreco", limitePreco);

        return query.getResultList();
    }

    /**
     * Recupera todos os alugués de um cliente específico.
     * @param nomeCliente Nome do Cliente.
     * @return Lista de imóveis disponíveis com valor de aluguel igual ou inferior ao informado.
     */
    public List<Aluguel> findAlugueisPorNomeCliente(String nomeCliente) {
        String jpql = "SELECT a FROM Aluguel a WHERE a.locacao.inquilino.nome LIKE :nomeCliente";

        TypedQuery<Aluguel> query = getManager().createQuery(jpql, Aluguel.class);
        query.setParameter("nomeCliente", "%" + nomeCliente + "%");

        return query.getResultList();
    }

    /**
     * Recupera todos os alugués de um pagos com atraso.
     * @return Lista de imóveis disponíveis com valor de aluguel igual ou inferior ao informado.
     */
    public List<Aluguel> findAluguéisPagosComAtraso() {
        String jpql = "SELECT a FROM Aluguel a WHERE a.dataPagamento IS NOT NULL " +
                "AND a.dataPagamento > a.dataVencimento";

        TypedQuery<Aluguel> query = getManager().createQuery(jpql, Aluguel.class);

        return query.getResultList();
    }
}
