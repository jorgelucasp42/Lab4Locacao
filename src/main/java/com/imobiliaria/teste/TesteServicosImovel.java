package com.imobiliaria.teste;

import com.imobiliaria.model.Imovel;
import com.imobiliaria.model.Profissional;
import com.imobiliaria.model.ServicoImovel;
import com.imobiliaria.repository.ImovelRepository;
import com.imobiliaria.repository.ProfissionalRepository;
import com.imobiliaria.repository.ServicoImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class TesteServicosImovel {
    private static EntityManagerFactory  factory = Persistence .createEntityManagerFactory("lab_jpa");
    private static EntityManager  manager = factory.createEntityManager();
    private static ServicoImovelRepository  servicoImovelRepository = new ServicoImovelRepository(manager);
    private static ImovelRepository imovelRepository = new ImovelRepository(manager);
    private static ProfissionalRepository profissionalRepository = new ProfissionalRepository(manager);
    public static void main(String[] args) {
        // OBSERVAÇÃO: Antes de Realizar os Seguintes Testes Crie um Profissional e Um Imovel nas
        // respectivas Classes de Testes
        try {
//            testCreate(1, 2);
//            testRead(1);
//            testUpdate(1);
            testDelete(2);
        } finally {
            manager.close();
            factory.close();
        }
    }

    private static void testCreate(int idImovel, int idProfissional) {
        System.out.println("Test Create");
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();

            Imovel  imovel = imovelRepository.buscaPorId(Imovel.class, idImovel); // Substitua pelo ID correto
            Profissional  profissional = profissionalRepository.buscaPorId(Profissional.class, idProfissional); // Substitua pelo ID correto

            ServicoImovel servicoImovel = new ServicoImovel();
            servicoImovel.setImovel(imovel);
            servicoImovel.setProfissional(profissional);
            servicoImovel.setDataServico(new Date() );
            servicoImovel.setValorTotal(500.00);
            servicoImovel.setObs("Reparo sala de estar");

            servicoImovel = servicoImovelRepository.salvaOuAtualiza(servicoImovel);
            System.out.println("ServicoImovel criado com ID: " + servicoImovel.getId());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    private static void testRead(int idServico) {
        System.out.println("Test Read");
        Integer id = idServico; // Substitua pelo ID correto que você espera que exista
        ServicoImovel servicoImovel = servicoImovelRepository.buscaPorId(ServicoImovel.class, id);
        if (servicoImovel != null) {
            System.out.println("ServicoImovel encontrado: " + servicoImovel.getProfissional().getNome() + ", " + servicoImovel.getImovel().getLogradouro());
        } else {
            System.out.println("ServicoImovel não encontrado com ID: " + id);
        }
    }

    private static void testUpdate(int idServico) {
        System.out.println("Test Update");
        Integer id = idServico; // Substitua pelo ID correto que você espera que exista
        ServicoImovel servicoImovel = servicoImovelRepository.buscaPorId(ServicoImovel.class, id);
        if (servicoImovel != null) {
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();

                servicoImovel.setValorTotal(600.00);
                servicoImovel.setObs("Atualização de observação");
                servicoImovel = servicoImovelRepository.salvaOuAtualiza(servicoImovel);

                transaction.commit();
                System.out.println("ServicoImovel atualziado com ID: " + servicoImovel.getId());
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("ServicoImovel não encontrado com ID: " + id);
        }
    }

    private static void testDelete(int idServico) {
        System.out.println("Test Delete");
        Integer id = idServico; // Substitua pelo ID correto que você espera que exista
        ServicoImovel servicoImovel = servicoImovelRepository.buscaPorId(ServicoImovel.class, id);
        if (servicoImovel != null) {
            EntityTransaction  transaction = manager.getTransaction();
            try {
                transaction.begin();

                servicoImovelRepository.remove(servicoImovel);

                transaction.commit();
                System.out.println("ServicoImovel deletado com ID: " + id);
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("ServicoImovel não encontrado com ID: " + id);
        }
    }
}
