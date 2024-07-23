package com.imobiliaria.teste;

import com.imobiliaria.model.Cliente;
import com.imobiliaria.model.Imovel;
import com.imobiliaria.model.Locacao;
import com.imobiliaria.repository.ClienteRepository;
import com.imobiliaria.repository.ImovelRepository;
import com.imobiliaria.repository.LocacaoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class TesteLocacao {
    private static EntityManagerFactory  factory = Persistence .createEntityManagerFactory("lab_jpa");
    private static EntityManager  manager = factory.createEntityManager();
    private static LocacaoRepository  locacaoRepository = new LocacaoRepository(manager);
    private static ImovelRepository imovelRepository = new ImovelRepository(manager);
    private static ClienteRepository clienteRepository = new ClienteRepository(manager);

    public static void main(String[] args) {
        // OBSERVAÇÃO: Antes de Realizar os Seguintes Testes Crie um Cliente e Um Imóvel nas
        // respectivas Classes de Testes
        try {
//            testCreate(1, 3);
//            testRead(1);
//            testUpdate(2);
            testDelete(2);
        } finally {
            manager.close();
            factory.close();
        }
    }
    private static void testCreate(int idCliente, int idImovel) {
        System.out.println("Test Create");
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();

            Imovel  imovel = imovelRepository.buscaPorId(Imovel.class, idImovel); // Substitua pelo ID correto
            Cliente  inquilino = clienteRepository.buscaPorId(Cliente.class, idCliente); // Substitua pelo ID correto

            Locacao locacao = new Locacao();
            locacao.setImovel(imovel);
            locacao.setInquilino(inquilino);
            locacao.setValorAluguel(1200.00);
            locacao.setPercentualMulta(2.0);
            locacao.setDiaVencimento((byte) 5);
            locacao.setDataInicio(new Date() );
            locacao.setAtivo(true);
            locacao.setObs("Observação de teste");

            locacao = locacaoRepository.salvaOuAtualiza(locacao);

            transaction.commit();
            System.out.println("Locacao cirada com ID: " + locacao.getId());
            System.out.println(locacao.toString());
        } catch (Exception e) {
            transaction.rollback();
        System.out.println("Error: " + e.getMessage());
        }
    }

    private static void testRead(int idLocacao) {
        System.out.println("Test Read");
        Integer id = idLocacao; // Substitua pelo ID correto que você espera que exista
        Locacao locacao = locacaoRepository.buscaPorId(Locacao.class, id);
        if (locacao != null) {
            System.out.println("Locacao encontrada: ");
            System.out.println(locacao.toString());
        } else {
            System.out.println("Locacao não encontrada ID: " + id);
        }
    }

    private static void testUpdate(int idLocacao) {
        System.out.println("Test Update");
        Integer id = idLocacao; // Substitua pelo ID correto que você espera que exista
        Locacao locacao = locacaoRepository.buscaPorId(Locacao.class, id);
        if (locacao != null) {
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();

                locacao.setValorAluguel(1300.00);
                locacao.setDiaVencimento((byte) 10);
                locacao.setAtivo(false);
                locacao = locacaoRepository.salvaOuAtualiza(locacao);

                transaction.commit();
                System.out.println("Locacao atualizada com ID: " + locacao.getId());
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Locacao não encontrada com ID: " + id);
        }
    }

    private static void testDelete(int idLocacao) {
        System.out.println("Test Delete");
        Integer id = idLocacao; // Substitua pelo ID correto que você espera que exista
        Locacao  locacao = locacaoRepository.buscaPorId(Locacao.class, id);
        if (locacao != null) {
            EntityTransaction  transaction = manager.getTransaction();
            try {
                transaction.begin();

                locacaoRepository.remove(locacao);
                System.out.println("Locacao deletada com ID: " + id);

                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Locacao não encontrada com ID: " + id);
        }
    }

}
