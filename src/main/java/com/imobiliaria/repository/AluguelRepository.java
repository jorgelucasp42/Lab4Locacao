package com.imobiliaria.repository;

import com.imobiliaria.model.Aluguel;

import javax.persistence.EntityManager;
import java.util.List;

public class AluguelRepository extends DAOGenerico<Aluguel> {
    public AluguelRepository(EntityManager manager) {
        super(manager);
    }

    public List<Aluguel> findAll() {
        return super.findAll(Aluguel.class);
    }
}
