package com.imobiliaria.repository;

import com.imobiliaria.model.TipoImovel;

import javax.persistence.EntityManager;
import java.util.List;

public class TipoImovelRepository extends DAOGenerico<TipoImovel> {
    public TipoImovelRepository(EntityManager manager) {
        super(manager);
    }

    public List<TipoImovel> findAll() {
        return super.findAll(TipoImovel.class);
    }
}

