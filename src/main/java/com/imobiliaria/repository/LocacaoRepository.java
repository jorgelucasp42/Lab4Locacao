package com.imobiliaria.repository;

import com.imobiliaria.model.Locacao;

import javax.persistence.EntityManager;
import java.util.List;

public class LocacaoRepository {

    private final EntityManager manager;
    private DAOGenerico<Locacao> daoGenerico;

    public LocacaoRepository(EntityManager manager) {
        this.manager = manager;
        daoGenerico = new DAOGenerico<>(manager);
    }

    public Locacao buscaPor(Integer id) {
        return daoGenerico.buscaPorId(Locacao.class, id);
    }

    public List<Locacao> buscaPorInquilinoId(Integer inquilinoId) {
        return this.manager.createQuery("from Locacao where inquilino.id = :inquilinoId", Locacao.class)
                .setParameter("inquilinoId", inquilinoId)
                .getResultList();
    }

    public List<Locacao> buscaTodos() {
        return this.manager.createQuery("from Locacao", Locacao.class).getResultList();
    }

    public Locacao salvaOuAtualiza(Locacao locacao) {
        return daoGenerico.salvaOuAtualiza(locacao);
    }

    public void remove(Locacao locacao) {
        daoGenerico.remove(locacao);
    }
}
