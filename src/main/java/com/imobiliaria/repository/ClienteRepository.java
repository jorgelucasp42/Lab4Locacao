package com.imobiliaria.repository;

import com.imobiliaria.model.Cliente;

import javax.persistence.EntityManager;

public class ClienteRepository extends DAOGenerico<Cliente> {
    public ClienteRepository(EntityManager manager) {
        super(manager);
    }
}
