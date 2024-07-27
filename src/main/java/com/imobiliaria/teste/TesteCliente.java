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
            System.out.println("========== Teste de Criação de Cliente ==========");
            testarCriacaoCliente();
            System.out.println("=================================================");

            System.out.println("========== Teste de Leitura de Cliente ==========");
            testarLeituraCliente();
            System.out.println("=================================================");

            System.out.println("========== Teste de Atualização de Cliente ==========");
            testarAtualizacaoCliente();
            System.out.println("=================================================");

            System.out.println("========== Teste de Remoção de Cliente ==========");
            testarRemocaoCliente();
            System.out.println("=================================================");
        } finally {
            manager.close();
            factory.close();
        }
    }

    private static void testarCriacaoCliente() {
        EntityTransaction transacao = manager.getTransaction();
        transacao.begin();

        Cliente cliente = new Cliente();
        cliente.setNome("João de Sousa");
        cliente.setCpf("12312312312");
        cliente.setTelefone("999999999");
        cliente.setEmail("joao@example.com");

        cliente = clienteRepository.salvaOuAtualiza(cliente);
        transacao.commit();

        Cliente clienteSalvo = clienteRepository.buscaPorId(Cliente.class, cliente.getId());
        if (clienteSalvo != null) {
            System.out.println("Cliente criado com sucesso: ");
            imprimirCliente(clienteSalvo);
        } else {
            System.out.println("Erro ao criar o cliente.");
        }
    }

    private static void testarLeituraCliente() {
        EntityTransaction transacao = manager.getTransaction();
        transacao.begin();

        Cliente cliente = new Cliente();
        cliente.setNome("Leitura Teste");
        cliente.setCpf("98765432100");
        cliente.setTelefone("999999999");
        cliente.setEmail("leitura@example.com");

        cliente = clienteRepository.salvaOuAtualiza(cliente);
        transacao.commit();

        Cliente clienteLido = clienteRepository.buscaPorId(Cliente.class, cliente.getId());
        if (clienteLido != null) {
            System.out.println("Cliente lido com sucesso: ");
            imprimirCliente(clienteLido);
        } else {
            System.out.println("Erro ao ler o cliente.");
        }
    }

    private static void testarAtualizacaoCliente() {
        EntityTransaction transacao = manager.getTransaction();
        transacao.begin();

        Cliente clienteExistente = clienteRepository.findAll(Cliente.class).get(0);
        clienteExistente.setNome("João Atualizado");
        clienteRepository.salvaOuAtualiza(clienteExistente);
        transacao.commit();

        Cliente clienteAtualizado = clienteRepository.buscaPorId(Cliente.class, clienteExistente.getId());
        if (clienteAtualizado != null) {
            System.out.println("Cliente atualizado com sucesso: ");
            imprimirCliente(clienteAtualizado);
        } else {
            System.out.println("Erro ao atualizar o cliente.");
        }
    }

    private static void testarRemocaoCliente() {
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

    private static void imprimirCliente(Cliente cliente) {
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Data de Nascimento: " + cliente.getDtNascimento());
        System.out.println("=====================================");
    }
}
