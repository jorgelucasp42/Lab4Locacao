package com.imobiliaria.repository;

import com.imobiliaria.model.Aluguel;

import javax.persistence.EntityManager;

public class AluguelRepository extends DAOGenerico<Aluguel> {
    public AluguelRepository(EntityManager manager) {
        super(manager);
    }
}
