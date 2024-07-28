package com.imobiliaria.teste;

import com.imobiliaria.model.Cliente;
import com.imobiliaria.repository.ClienteRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TesteCliente {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
    private static EntityManager manager = factory.createEntityManager();
    private static ClienteRepository clienteRepository = new ClienteRepository(manager);

    public static void main(String[] args) {
        try {
            System.out.println("========== Teste de Criação de Clientes ==========");
           // testarCriacaoClientes();

            System.out.println("========== Teste de Leitura de Cliente ==========");
            //testarLeituraCliente();

            System.out.println("========== Teste de Atualização de Cliente ==========");
           testarAtualizacaoCliente();

            System.out.println("========== Teste de Remoção de Cliente ==========");
           // testarRemocaoCliente();

            System.out.println("========== Teste de Unicidade de CPF ==========");
            //testarUnicidadeCPF();
        } finally {
            manager.close();
            factory.close();
        }
    }
/*
    private static void testarCriacaoClientes() {
        EntityTransaction transacao = manager.getTransaction();
        transacao.begin();

        Cliente cliente1 = new Cliente(null, "João de Sousa", "12312312312", "999999999", "joao@example.com", parseDate("1990-01-01"), new ArrayList<>(), new ArrayList<>());
        Cliente cliente2 = new Cliente(null, "Maria de Souza", "45645645645", "888888888", "maria@example.com", parseDate("1985-05-15"), new ArrayList<>(), new ArrayList<>());
        Cliente cliente3 = new Cliente(null, "Carlos de Almeida", "78978978978", "777777777", "carlos@example.com", parseDate("1992-03-03"), new ArrayList<>(), new ArrayList<>());

        cliente1 = clienteRepository.salvaOuAtualiza(cliente1);
        cliente2 = clienteRepository.salvaOuAtualiza(cliente2);
        cliente3 = clienteRepository.salvaOuAtualiza(cliente3);

        transacao.commit();

        System.out.println("Clientes criados com sucesso.");
        imprimirCliente(cliente1);
        imprimirCliente(cliente2);
        imprimirCliente(cliente3);
    }

    private static void testarLeituraCliente() {
        Cliente clienteLido = clienteRepository.findAll(Cliente.class).get(0);
        if (clienteLido != null) {
            System.out.println("Cliente lido com sucesso: ");
            imprimirCliente(clienteLido);
        } else {
            System.out.println("Erro ao ler o cliente.");
        }
    }
*/
    private static void testarAtualizacaoCliente() {
        EntityTransaction transacao = manager.getTransaction();
        transacao.begin();

        Cliente clienteExistente = clienteRepository.findAll(Cliente.class).get(0);
        clienteExistente.setNome("João Atualizado");
        clienteExistente.setDtNascimento(parseDate("1991-02-02"));
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

    /*
    private static void testarRemocaoCliente() {
        EntityTransaction transacao = manager.getTransaction();
        transacao.begin();

        List<Cliente> clientes = clienteRepository.findAll(Cliente.class);
        if (!clientes.isEmpty()) {
            Cliente clienteExistente = clientes.get(0);
            clienteRepository.remove(clienteExistente);
            transacao.commit();

            Cliente clienteRemovido = clienteRepository.buscaPorId(Cliente.class, clienteExistente.getId());
            if (clienteRemovido == null) {
                System.out.println("Cliente removido com sucesso.");
            } else {
                System.out.println("Erro ao remover o cliente.");
            }
        } else {
            System.out.println("Nenhum cliente encontrado para remoção.");
            transacao.rollback();
        }
    }*/
/*
    private static void testarUnicidadeCPF() {
        EntityTransaction transacao = manager.getTransaction();
        transacao.begin();

        String cpfDuplicado = "12312312312"; // CPF que ainda está no banco de dados
        Cliente clienteDuplicado = new Cliente(null, "Teste Duplicado", cpfDuplicado, "666666666", "duplicado@example.com", parseDate("2000-01-01"), new ArrayList<>(), new ArrayList<>());

        try {
            if (clienteRepository.isCpfUnique(cpfDuplicado)) {
                clienteRepository.salvaOuAtualiza(clienteDuplicado);
                transacao.commit();
                System.out.println("Erro: Deveria ter falhado ao criar cliente com CPF duplicado.");
            } else {
                throw new Exception("CPF duplicado detectado");
            }
        } catch (Exception e) {
            transacao.rollback();
            System.out.println("Unicidade de CPF verificada: " + e.getMessage());
        }
    }
    */

    private static void imprimirCliente(Cliente cliente) {
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("Email: " + cliente.getEmail());
        System.out.println("Data de Nascimento: " + new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDtNascimento()));
        System.out.println("=====================================");
    }

    private static Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
