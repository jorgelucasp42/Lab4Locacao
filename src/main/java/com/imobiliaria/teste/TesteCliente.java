package com.imobiliaria.teste;

import com.imobiliaria.model.Cliente;
import com.imobiliaria.service.ClienteService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class TesteCliente {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ClienteService clienteService = new ClienteService(manager);

        EntityTransaction transacao = manager.getTransaction();
        transacao.begin();

        // Cenário: criar e salvar um cliente
        Cliente cliente = new Cliente();
        cliente.setNome("João de Sousa");
        cliente.setCpf("123.123.123-12");
        cliente.setTelefone("999999999");
        cliente.setEmail("joao@example.com");

        // Ação: salvar o cliente
        clienteService.salvaOuAtualiza(cliente);

        transacao.commit();

        // Verificação: verificar se o cliente foi salvo
        Cliente clienteSalvo = clienteService.buscaPorId(cliente.getId());
        if (clienteSalvo != null) {
            System.out.println("Cliente salvo com sucesso: " + clienteSalvo.getNome());
        } else {
            System.out.println("Erro ao salvar o cliente.");
        }

        // Cenário: tentar salvar outro cliente com o mesmo CPF
        Cliente clienteDuplicado = new Cliente();
        clienteDuplicado.setNome("Maria de Sousa");
        clienteDuplicado.setCpf("123.123.123-12");
        clienteDuplicado.setTelefone("888888888");
        clienteDuplicado.setEmail("maria@example.com");

        transacao.begin();
        try {
            clienteService.salvaOuAtualiza(clienteDuplicado);
            transacao.commit();
            System.out.println("Cliente duplicado salvo, problema com a verificação de CPF único.");
        } catch (Exception e) {
            transacao.rollback();
            System.out.println("Erro ao salvar cliente duplicado (CPF deve ser único): " + e.getMessage());
        }

        // Cenário: listar todos os clientes
        List<Cliente> clientes = clienteService.findAll();
        System.out.println("Clientes cadastrados:");
        for (Cliente c : clientes) {
            System.out.println(c.getNome() + " - CPF: " + c.getCpf());
        }

        // Cenário: atualizar um cliente
        transacao.begin();
        clienteSalvo.setNome("João de Sousa Atualizado");
        clienteService.salvaOuAtualiza(clienteSalvo);
        transacao.commit();

        Cliente clienteAtualizado = clienteService.buscaPorId(clienteSalvo.getId());
        System.out.println("Cliente atualizado: " + clienteAtualizado.getNome());

        // Cenário: remover um cliente
        transacao.begin();
        clienteService.remove(clienteAtualizado);
        transacao.commit();

        Cliente clienteRemovido = clienteService.buscaPorId(clienteAtualizado.getId());
        if (clienteRemovido == null) {
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Erro ao remover o cliente.");
        }

        manager.close();
        factory.close();
    }
}
