package com.imobiliaria.teste;

import com.imobiliaria.model.TipoImovel;
import com.imobiliaria.repository.TipoImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class TesteTipoImovel {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
    private static EntityManager manager = factory.createEntityManager();
    private static TipoImovelRepository tipoImovelRepository = new TipoImovelRepository(manager);

    public static void main(String[] args) {
        try {
            System.out.println("========== Teste de Criação de Tipos de Imóveis ==========");
            criarTiposImovel();

            System.out.println("========== Teste de Leitura de Tipo de Imóvel ==========");
            lerTipoImovel(1);

            System.out.println("========== Teste de Atualização de Tipo de Imóvel ==========");
           atualizarTipoImovel(1);

            System.out.println("========== Teste de Remoção de Tipo de Imóvel ==========");
            removerTipoImovel(3);
        } finally {
            manager.close();
            factory.close();
        }
    }

    private static void criarTiposImovel() {

        EntityTransaction transacao = manager.getTransaction();
        try {
            transacao.begin() ;

            TipoImovel tipo1 = new TipoImovel(null, "Apartamento", new ArrayList<>());
            TipoImovel tipo2 = new TipoImovel(null, "Casa", new ArrayList<>());
            TipoImovel tipo3 = new TipoImovel(null, "Loja", new ArrayList<>());

            tipo1 = tipoImovelRepository.salvaOuAtualiza(tipo1);
            tipo2 = tipoImovelRepository.salvaOuAtualiza(tipo2);
            tipo3 = tipoImovelRepository.salvaOuAtualiza(tipo3);

            System.out.println("Tipos de Imóvel criados com sucesso.");
            imprimirTipoImovel(tipo1);
            imprimirTipoImovel(tipo2);
            imprimirTipoImovel(tipo3);

            transacao.commit();
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        }
    }

    private static void lerTipoImovel(int idTipoImovel) {
        System.out.println("Teste de Leitura");
        TipoImovel tipoImovel = tipoImovelRepository.buscaPorId(TipoImovel.class, idTipoImovel);
        if (tipoImovel != null) {
            System.out.println("Tipo de Imóvel encontrado: ");
            imprimirTipoImovel(tipoImovel);
        } else {
            System.out.println("Tipo de Imóvel não encontrado com ID: " + idTipoImovel);
        }
    }

    private static void atualizarTipoImovel(int idTipoImovel) {
        System.out.println("Teste de Atualização");
        TipoImovel tipoImovel = tipoImovelRepository.buscaPorId(TipoImovel.class, idTipoImovel);
        if (tipoImovel != null) {
            EntityTransaction transacao = manager.getTransaction();
            try {
                transacao.begin();

                tipoImovel.setDescricao("Apartamento Atualizado");
                tipoImovel = tipoImovelRepository.salvaOuAtualiza(tipoImovel);

                transacao.commit();
                System.out.println("Tipo de Imóvel atualizado com sucesso: ");
                imprimirTipoImovel(tipoImovel);
            } catch (Exception e) {
                transacao.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Tipo de Imóvel não encontrado com ID: " + idTipoImovel);
        }
    }

    private static void removerTipoImovel(int idTipoImovel) {
        System.out.println("Remoção do ID:"+ idTipoImovel+".");
        TipoImovel tipoImovel = tipoImovelRepository.buscaPorId(TipoImovel.class, idTipoImovel);
        if (tipoImovel != null) {
            EntityTransaction transacao = manager.getTransaction();
            try {
                transacao.begin();

                tipoImovelRepository.remove(tipoImovel);
                System.out.println("Tipo de Imóvel removido com sucesso: " + idTipoImovel+".");

                transacao.commit();
            } catch (Exception e) {
                transacao.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Tipo de Imóvel não encontrado com ID: " + idTipoImovel+".");
        }
    }

    private static void imprimirTipoImovel(TipoImovel tipoImovel) {
        System.out.println("ID: " + tipoImovel.getId());
        System.out.println("Descrição: " + tipoImovel.getDescricao());
        System.out.println("**************************************");
    }
}
