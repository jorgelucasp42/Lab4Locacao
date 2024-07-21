package com.imobiliaria.repository;

import com.imobiliaria.model.Imovel;

import javax.persistence.EntityManager;
import java.util.List;

public class ImovelRepository extends DAOGenerico<Imovel> {
    public ImovelRepository(EntityManager manager) {
        super(manager);
    }

    public List<Imovel> findAll() {
        return super.findAll(Imovel.class);
    }
}
