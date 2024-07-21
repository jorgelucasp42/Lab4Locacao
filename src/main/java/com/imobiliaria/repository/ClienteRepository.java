package com.imobiliaria.repository;

import com.imobiliaria.model.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteRepository extends DAOGenerico<Cliente> {
    public ClienteRepository(EntityManager manager) {
        super(manager);
    }

    public List<Cliente> findAll() {
        return super.findAll(Cliente.class);
    }
}
