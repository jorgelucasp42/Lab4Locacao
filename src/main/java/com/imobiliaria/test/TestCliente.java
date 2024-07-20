package com.imobiliaria.test;

import com.imobiliaria.model.Cliente;
import com.imobiliaria.repository.ClienteRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class TestCliente {

    public static void main(String[] args) {
        EntityManager manager = JPAUtil.getEntityManager();
        ClienteRepository clienteRepository = new ClienteRepository(manager);

        // Criar um novo cliente
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setCpf("12345678901");
        cliente.setTelefone("999999999");
        cliente.setEmail("joao.silva@example.com");
        cliente.setDtNascimento(new java.util.Date());

        manager.getTransaction().begin();
        Cliente savedCliente = clienteRepository.salvaOuAtualiza(cliente);
        manager.getTransaction().commit();
        System.out.println("Cliente salvo: " + savedCliente);

        // Buscar cliente por nome
        List<Cliente> clientes = clienteRepository.buscaPor("João");
        clientes.forEach(c -> System.out.println("Cliente encontrado: " + c.getNome()));

        // Verificar unicidade do CPF
        boolean cpfExists = clienteRepository.existePorCpf("12345678901");
        System.out.println("CPF já cadastrado? " + cpfExists);

        // Atualizar cliente
        savedCliente.setTelefone("888888888");
        manager.getTransaction().begin();
        Cliente updatedCliente = clienteRepository.salvaOuAtualiza(savedCliente);
        manager.getTransaction().commit();
        System.out.println("Cliente atualizado: " + updatedCliente);

        // Remover cliente
        manager.getTransaction().begin();
        clienteRepository.remove(updatedCliente);
        manager.getTransaction().commit();
        System.out.println("Cliente removido");

        manager.close();
    }
}
