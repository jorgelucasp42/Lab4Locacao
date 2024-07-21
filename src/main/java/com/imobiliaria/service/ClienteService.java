package com.imobiliaria.service;

import com.imobiliaria.model.Cliente;
import com.imobiliaria.repository.ClienteRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(EntityManager em) {
        this.clienteRepository = new ClienteRepository(em);
    }

    public Cliente buscaPorId(Integer id) {
        return clienteRepository.buscaPorId(Cliente.class, id);
    }

    public Cliente salvaOuAtualiza(Cliente cliente) {
        return clienteRepository.salvaOuAtualiza(cliente);
    }

    public void remove(Cliente cliente) {
        clienteRepository.remove(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}
