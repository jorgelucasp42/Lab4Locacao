package com.imobiliaria.test;

import com.imobiliaria.model.Cliente;
import com.imobiliaria.repository.ClienteRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.Date;

public class TesteCliente {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        ClienteRepository clienteRepository = new ClienteRepository(em);

        Cliente cliente = new Cliente();
        cliente.setNome("Maria da Silva");
        cliente.setCpf("12345678901");
        cliente.setTelefone("11999999999");
        cliente.setEmail("maria.silva@example.com");
        cliente.setDtNascimento(new Date());

        em.getTransaction().begin();
        clienteRepository.salvaOuAtualiza(cliente);
        em.getTransaction().commit();

        Cliente clienteEncontrado = clienteRepository.buscaPorId(cliente.getId());
        System.out.println("Cliente encontrado: " + clienteEncontrado.getNome());

        em.close();
    }
}
