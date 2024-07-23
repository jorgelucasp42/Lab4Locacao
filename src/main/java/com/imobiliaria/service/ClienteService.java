package com.imobiliaria.service;

import com.imobiliaria.repository.ClienteRepository;

import javax.persistence.EntityManager;

public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(EntityManager em) {
        this.clienteRepository = new ClienteRepository(em);
    }

}
