package com.imobiliaria.teste;

import com.imobiliaria.model.Profissional;
import com.imobiliaria.repository.ProfissionalRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;

// OBSERVAÇÃO: Execute antes a classe TesteServicosImovel

public class TesteProfissional {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
    private static EntityManager manager = factory.createEntityManager();
    private static ProfissionalRepository profissionalRepository = new ProfissionalRepository(manager);

    public static void main(String[] args) {
        try {
            System.out.println("========== Teste de Criação de Profissionais ==========");
            criarProfissional();

            System.out.println("========== Teste de Leitura de Profissional ==========");
            lerProfissional(1);

            System.out.println("========== Teste de Atualização de Profissional ==========");
            atualizarProfissional(1);

            System.out.println("========== Teste de Remoção de Profissional ==========");
            removerProfissional(2);
        } finally {
            manager.close();
            factory.close();
        }
    }

    private static void criarProfissional() {
        EntityTransaction transacao = manager.getTransaction();
        try {
            transacao.begin();

            Profissional profissional1 = new Profissional(null, "Mariano Santos", "Encanador", "987987987", "123123123", 50.00, "Profissional qualificado", new ArrayList<>());
            Profissional profissional2 = new Profissional(null, "Ana Paula", "Eletricista", "987654321", "321321321", 60.00, "Profissional experiente", new ArrayList<>());

            profissional1 = profissionalRepository.salvaOuAtualiza(profissional1);
            profissional2 = profissionalRepository.salvaOuAtualiza(profissional2);

            transacao.commit();

            imprimirProfissional(profissional1);
            imprimirProfissional(profissional2);

            System.out.println("Profissionais criados com sucesso.");
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        }
    }

    private static void lerProfissional(int idProfissional) {
        Profissional profissional = profissionalRepository.buscaPorId(Profissional.class, idProfissional);
        if (profissional != null) {
            System.out.println("Profissional encontrado:");
            imprimirProfissional(profissional);
        } else {
            System.out.println("Profissional não encontrado com ID: " + idProfissional);
        }
    }

    private static void atualizarProfissional(int idProfissional) {
        Profissional profissional = profissionalRepository.buscaPorId(Profissional.class, idProfissional);
        if (profissional != null) {
            EntityTransaction transacao = manager.getTransaction();
            try {
                transacao.begin();

                profissional.setTelefone1("1122334455");
                profissional.setValorHora(60.00);
                profissional.setObs("Atualização de observação");
                profissional = profissionalRepository.salvaOuAtualiza(profissional);

                transacao.commit();

                imprimirProfissional(profissional);
                System.out.println("Profissional atualizado com sucesso.");
            } catch (Exception e) {
                transacao.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Profissional não encontrado com ID: " + idProfissional);
        }
    }

    private static void removerProfissional(int idProfissional) {
        Profissional profissional = profissionalRepository.buscaPorId(Profissional.class, idProfissional);
        if (profissional != null) {
            EntityTransaction transacao = manager.getTransaction();
            try {
                transacao.begin();

                profissionalRepository.remove(profissional);
                transacao.commit();
                System.out.println("Profissional removido com sucesso.");
            } catch (Exception e) {
                transacao.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Profissional não encontrado com ID: " + idProfissional);
        }
    }

    private static void imprimirProfissional(Profissional profissional) {
        System.out.println("ID: " + profissional.getId());
        System.out.println("Nome: " + profissional.getNome());
        System.out.println("Profissão: " + profissional.getProfissao());
        System.out.println("Telefone 1: " + profissional.getTelefone1());
        System.out.println("Telefone 2: " + profissional.getTelefone2());
        System.out.println("Valor Hora: " + profissional.getValorHora());
        System.out.println("Observações: " + profissional.getObs());
        System.out.println("=====================================");
    }
}
