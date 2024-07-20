package com.imobiliaria.repository;

import com.imobiliaria.model.Aluguel;

import javax.persistence.EntityManager;
import java.util.List;

public class AluguelRepository {

    private final EntityManager manager;
    private DAOGenerico<Aluguel> daoGenerico;

    public AluguelRepository(EntityManager manager) {
        this.manager = manager;
        daoGenerico = new DAOGenerico<>(manager);
    }

    public Aluguel buscaPor(Integer id) {
        return daoGenerico.buscaPorId(Aluguel.class, id);
    }

    public List<Aluguel> buscaPorInquilinoNome(String nome) {
        return this.manager.createQuery("from Aluguel a where a.locacao.inquilino.nome = :nome", Aluguel.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Aluguel> buscaTodos() {
        return this.manager.createQuery("from Aluguel", Aluguel.class).getResultList();
    }

    public Aluguel salvaOuAtualiza(Aluguel aluguel) {
        return daoGenerico.salvaOuAtualiza(aluguel);
    }

    public void remove(Aluguel aluguel) {
        daoGenerico.remove(aluguel);
    }

    public List<Aluguel> buscaAtrasados() {
        return this.manager.createQuery("from Aluguel a where a.dataPagamento > a.dataVencimento", Aluguel.class)
                .getResultList();
    }
}
