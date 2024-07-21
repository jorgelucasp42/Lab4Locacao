package com.imobiliaria.repository;

import com.imobiliaria.model.Profissional;

import javax.persistence.EntityManager;

public class ProfissionalRepository extends DAOGenerico<Profissional> {
    public ProfissionalRepository(EntityManager manager) {
        super(manager);
    }
}
