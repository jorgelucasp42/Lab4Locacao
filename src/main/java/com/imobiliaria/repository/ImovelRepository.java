package com.imobiliaria.repository;

import com.imobiliaria.model.Imovel;

import javax.persistence.EntityManager;

public class ImovelRepository extends DAOGenerico<Imovel> {
    public ImovelRepository(EntityManager em) {
        super(Imovel.class, em);
    }
}
