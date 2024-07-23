package com.imobiliaria.teste;

import com.imobiliaria.model.TipoImovel;
import com.imobiliaria.repository.TipoImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TesteTipoImovel {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
    private static EntityManager manager = factory.createEntityManager();
    private static TipoImovelRepository tipoImovelRepository = new TipoImovelRepository(manager);

    public static void main(String[] args) {
        try {
            testCreate();
//            testRead(1);
//            testUpdate(1);
//            testDelete(1);
        } finally {
            manager.close();
            factory.close();
        }

    }

    private static void testCreate() {
        System.out.println("Test Create");
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();

            TipoImovel tipoImovel = new TipoImovel();
            tipoImovel.setDescricao("Apartamento");

            tipoImovel = tipoImovelRepository.salvaOuAtualiza(tipoImovel);
            System.out.println("TipoImovel criado com ID: " + tipoImovel.getId());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    private static void testRead(int idTipoImovel) {
        System.out.println("Test Read");
        Integer id = idTipoImovel;
        TipoImovel tipoImovel = tipoImovelRepository.buscaPorId(TipoImovel.class, id);
        if (tipoImovel != null) {
            System.out.println("TipoImovel encontrado: " + tipoImovel.getDescricao());
        } else {
            System.out.println("TipoImovel não encontrado com ID: " + id);
        }
    }

    private static void testUpdate(int idTipoImovel) {
        System.out.println("Test Update");
        Integer id = idTipoImovel;
        TipoImovel tipoImovel = tipoImovelRepository.buscaPorId(TipoImovel.class, id);
        if (tipoImovel != null) {
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();

                tipoImovel.setDescricao("Apartamento Atualizado");
                tipoImovel = tipoImovelRepository.salvaOuAtualiza(tipoImovel);

                transaction.commit();
                System.out.println("TipoImovel atualizado com ID: " + tipoImovel.getId());
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("TipoImovel não encontrado com ID: " + id);
        }
    }

    private static void testDelete(int idTipoImovel) {
        System.out.println("Test Delete");
        Integer id = idTipoImovel;
        TipoImovel tipoImovel = tipoImovelRepository.buscaPorId(TipoImovel.class, id);
        if (tipoImovel != null) {
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();

                tipoImovelRepository.remove(tipoImovel);
                System.out.println("TipoImovel deletado com ID: " + id);

                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("TipoImovel não encontrado com ID: " + id);
        }
    }
}
