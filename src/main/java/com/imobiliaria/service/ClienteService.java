package com.imobiliaria.service;

import com.imobiliaria.model.Cliente;
import com.imobiliaria.repository.ClienteRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(EntityManager manager) {
        this.clienteRepository = new ClienteRepository(manager);
    }

    public Cliente salvarCliente(Cliente cliente) {
        // Verificar se o CPF do cliente é único
        List<Cliente> clientesExistentes = clienteRepository.buscaPorCpf(cliente.getCpf());
        if (!clientesExistentes.isEmpty()) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        return clienteRepository.salvaOuAtualiza(cliente);
    }

    public Cliente atualizarCliente(Cliente cliente) {
        return clienteRepository.salvaOuAtualiza(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.buscaTodos();
    }

    public Cliente buscarClientePorId(Integer id) {
        return clienteRepository.buscaPorId(id);
    }

    public void removerCliente(Cliente cliente) {
        clienteRepository.remove(cliente);
    }
}
