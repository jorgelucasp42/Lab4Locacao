package com.imobiliaria.repository;

import com.imobiliaria.model.TipoImovel;

import javax.persistence.EntityManager;

public class TipoImovelRepository extends DAOGenerico<TipoImovel> {
    public TipoImovelRepository(EntityManager manager) {
        super(manager);
    }
}

