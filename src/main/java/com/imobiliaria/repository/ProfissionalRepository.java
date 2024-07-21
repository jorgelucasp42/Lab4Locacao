package com.imobiliaria.repository;

import com.imobiliaria.model.Profissional;

import javax.persistence.EntityManager;
import java.util.List;

public class ProfissionalRepository extends DAOGenerico<Profissional> {
    public ProfissionalRepository(EntityManager manager) {
        super(manager);
    }

    public List<Profissional> findAll() {
        return super.findAll(Profissional.class);
    }
}
