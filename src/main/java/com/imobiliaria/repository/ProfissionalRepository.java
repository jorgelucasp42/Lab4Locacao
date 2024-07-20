package com.imobiliaria.repository;

import com.imobiliaria.model.Profissional;

import javax.persistence.EntityManager;
import java.util.List;

public class ProfissionalRepository {

    private final EntityManager manager;
    private DAOGenerico<Profissional> daoGenerico;

    public ProfissionalRepository(EntityManager manager) {
        this.manager = manager;
        daoGenerico = new DAOGenerico<>(manager);
    }

    public Profissional buscaPor(Integer id) {
        return daoGenerico.buscaPorId(Profissional.class, id);
    }

    public List<Profissional> buscaTodos() {
        return this.manager.createQuery("from Profissional", Profissional.class).getResultList();
    }

    public Profissional salvaOuAtualiza(Profissional profissional) {
        return daoGenerico.salvaOuAtualiza(profissional);
    }

    public void remove(Profissional profissional) {
        daoGenerico.remove(profissional);
    }
}
