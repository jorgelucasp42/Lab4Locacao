package com.imobiliaria.repository;

import com.imobiliaria.model.ServicoImovel;

import javax.persistence.EntityManager;

public class ServicoImovelRepository extends DAOGenerico<ServicoImovel> {
    public ServicoImovelRepository(EntityManager manager) {
        super(manager);
    }
}
