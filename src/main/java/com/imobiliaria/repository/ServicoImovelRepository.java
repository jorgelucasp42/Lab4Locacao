package com.imobiliaria.repository;

import com.imobiliaria.model.ServicoImovel;

import javax.persistence.EntityManager;
import java.util.List;

public class ServicoImovelRepository extends DAOGenerico<ServicoImovel> {
    public ServicoImovelRepository(EntityManager manager) {
        super(manager);
    }

    public List<ServicoImovel> findAll() {
        return super.findAll(ServicoImovel.class);
    }
}
