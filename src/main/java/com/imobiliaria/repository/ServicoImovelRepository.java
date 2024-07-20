package com.imobiliaria.repository;

import com.imobiliaria.model.ServicoImovel;

import javax.persistence.EntityManager;
import java.util.List;

public class ServicoImovelRepository {

    private final EntityManager manager;
    private DAOGenerico<ServicoImovel> daoGenerico;

    public ServicoImovelRepository(EntityManager manager) {
        this.manager = manager;
        daoGenerico = new DAOGenerico<>(manager);
    }

    public ServicoImovel buscaPor(Integer id) {
        return daoGenerico.buscaPorId(ServicoImovel.class, id);
    }

    public List<ServicoImovel> buscaTodos() {
        return this.manager.createQuery("from ServicoImovel", ServicoImovel.class).getResultList();
    }

    public ServicoImovel salvaOuAtualiza(ServicoImovel servicoImovel) {
        return daoGenerico.salvaOuAtualiza(servicoImovel);
    }

    public void remove(ServicoImovel servicoImovel) {
        daoGenerico.remove(servicoImovel);
    }
}

