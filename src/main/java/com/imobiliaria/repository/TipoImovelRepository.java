package com.imobiliaria.repository;

import com.imobiliaria.model.TipoImovel;

import javax.persistence.EntityManager;
import java.util.List;

public class TipoImovelRepository {

    private final EntityManager manager;
    private DAOGenerico<TipoImovel> daoGenerico;

    public TipoImovelRepository(EntityManager manager) {
        this.manager = manager;
        daoGenerico = new DAOGenerico<>(manager);
    }

    public TipoImovel buscaPor(Integer id) {
        return daoGenerico.buscaPorId(TipoImovel.class, id);
    }

    public List<TipoImovel> buscaTodos() {
        return this.manager.createQuery("from TipoImovel", TipoImovel.class).getResultList();
    }

    public TipoImovel salvaOuAtualiza(TipoImovel tipoImovel) {
        return daoGenerico.salvaOuAtualiza(tipoImovel);
    }

    public void remove(TipoImovel tipoImovel) {
        daoGenerico.remove(tipoImovel);
    }
}
