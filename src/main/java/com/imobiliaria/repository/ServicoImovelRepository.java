package com.imobiliaria.repository;

import com.imobiliaria.model.ServicoImovel;

import javax.persistence.EntityManager;

public class ServicoImovelRepository {

    private final EntityManager manager;
    private final DAOGenerico<ServicoImovel> daoGenerico;

    public ServicoImovelRepository(EntityManager manager) {
        this.manager = manager;
        this.daoGenerico = new DAOGenerico<>(manager, ServicoImovel.class);
    }

    public ServicoImovel buscaPorId(Integer id) {
        return daoGenerico.buscaPorId(id);
    }

    public ServicoImovel salvaOuAtualiza(ServicoImovel servicoImovel) {
        return daoGenerico.salvaOuAtualiza(servicoImovel);
    }

    public void remove(ServicoImovel servicoImovel) {
        daoGenerico.remove(servicoImovel);
    }

    public List<ServicoImovel> buscaTodos() {
        return daoGenerico.buscaTodos();
    }
}

