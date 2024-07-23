package com.imobiliaria.teste;

import com.imobiliaria.model.Profissional;
import com.imobiliaria.repository.ProfissionalRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TesteProfissional {
    private static EntityManagerFactory  factory = Persistence .createEntityManagerFactory("lab_jpa");
    private static EntityManager manager = factory.createEntityManager();
    private static ProfissionalRepository  profissionalRepository = new ProfissionalRepository(manager);
    public static void main(String[] args) {
        try {
//            testCreate();
//            testRead(1);
//            testUpdate(1);
            testDelete(2);
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

            Profissional profissional = new Profissional();
            profissional.setNome("Mariano Santos");
            profissional.setProfissao("Encanador");
            profissional.setTelefone1("987987987");
            profissional.setTelefone2("123123123");
            profissional.setValorHora(50.00);
            profissional.setObs("Profissional qualificado");

            profissional = profissionalRepository.salvaOuAtualiza(profissional);

            transaction.commit();
            System.out.println("Profissional criado com ID: " + profissional.getId());
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    private static void testRead(int idProfissional) {
        System.out.println("Test Read");
        Integer id = idProfissional; // Substitua pelo ID correto que você espera que exista
        Profissional profissional = profissionalRepository.buscaPorId(Profissional.class, id);
        if (profissional != null) {
            System.out.println("Profissional encontrado: " + profissional.getNome() + ", " + profissional.getProfissao());
        } else {
            System.out.println("Profissional não encontrado com ID: " + id);
        }
    }

    private static void testUpdate(int idProfissional) {
        System.out.println("Test Update");
        Integer id = idProfissional; // Substitua pelo ID correto que você espera que exista
        Profissional profissional = profissionalRepository.buscaPorId(Profissional.class, id);
        if (profissional != null) {
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();

                profissional.setTelefone1("1122334455");
                profissional.setValorHora(60.00);
                profissional.setObs("Atualização de observação");
                profissional = profissionalRepository.salvaOuAtualiza(profissional);

                transaction.commit();
                System.out.println("Profissional atualizado com ID: " + profissional.getId());
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Profissional não encontrado com ID: " + id);
        }
    }

    private static void testDelete(int idProfissional) {
        System.out.println("Test Delete");
        Integer id = idProfissional; // Substitua pelo ID correto que você espera que exista
        Profissional  profissional = profissionalRepository.buscaPorId(Profissional.class, id);
        if (profissional != null) {
            EntityTransaction  transaction = manager.getTransaction();
            try {
                transaction.begin();

                profissionalRepository.remove(profissional);
                System.out.println("Profissional deletado com ID: " + id);

                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Profissional não encontrado com ID: " + id);
        }
    }
}
