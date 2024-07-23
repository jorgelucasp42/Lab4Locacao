package com.imobiliaria.teste;

import com.imobiliaria.model.Cliente;
import com.imobiliaria.repository.ClienteRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class TesteCliente {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
    private static EntityManager manager = factory.createEntityManager();
    private static ClienteRepository clienteRepository = new ClienteRepository(manager);

    public static void main(String[] args) {
        try {
//            testCreate();
            testRead();
//            testUpdate();
//            testDelete();
        } finally {
            manager.close();
            factory.close();
        }

    }

    private static void testCreate() {
        EntityTransaction transacao = manager.getTransaction();
        transacao.begin();

        Cliente cliente = new Cliente();
        cliente.setNome("João de Sousa");
        cliente.setCpf("12312312312");
        cliente.setTelefone("999999999");
        cliente.setEmail("joao@example.com");

        clienteRepository.salvaOuAtualiza(cliente);
        transacao.commit();

        Cliente clienteSalvo = clienteRepository.buscaPorId(Cliente.class, cliente.getId());
        if (clienteSalvo != null) {
            System.out.println("Cliente criado com sucesso: " + clienteSalvo.getNome());
        } else {
            System.out.println("Erro ao criar o cliente.");
        }
    }

    private static void testRead() {
        Cliente cliente = new Cliente();
        cliente.setNome("Leitura Teste");
        cliente.setCpf("98765432100");
        cliente.setTelefone("999999999");
        cliente.setEmail("leitura@example.com");

        EntityTransaction transacao = manager.getTransaction();
        transacao.begin();
        clienteRepository.salvaOuAtualiza(cliente);
        transacao.commit();

        Cliente clienteLido = clienteRepository.buscaPorId(Cliente.class, cliente.getId());
        if (clienteLido != null) {
            System.out.println("Cliente lido: " + clienteLido.getNome() + " - CPF: " + clienteLido.getCpf());
        } else {
            System.out.println("Erro ao ler o cliente.");
        }
    }

    private static void testUpdate() {
        EntityTransaction transacao = manager.getTransaction();
        transacao.begin();

        Cliente clienteExistente = clienteRepository.findAll(Cliente.class).get(0);
        clienteExistente.setNome("João Atualizado");
        clienteRepository.salvaOuAtualiza(clienteExistente);
        transacao.commit();

        Cliente clienteAtualizado = clienteRepository.buscaPorId(Cliente.class, clienteExistente.getId());
        System.out.println("Cliente atualizado: " + clienteAtualizado.getNome());
    }

    private static void testDelete() {
        EntityTransaction transacao = manager.getTransaction();
        transacao.begin();

        Cliente clienteExistente = clienteRepository.findAll(Cliente.class).get(0);
        clienteRepository.remove(clienteExistente);
        transacao.commit();

        Cliente clienteRemovido = clienteRepository.buscaPorId(Cliente.class, clienteExistente.getId());
        if (clienteRemovido == null) {
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Erro ao remover o cliente.");
        }
    }
}
