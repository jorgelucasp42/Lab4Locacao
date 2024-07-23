package com.imobiliaria.teste;

import com.imobiliaria.model.Aluguel;
import com.imobiliaria.model.Imovel;
import com.imobiliaria.model.Locacao;
import com.imobiliaria.repository.AluguelRepository;
import com.imobiliaria.service.AluguelService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class TesteAluguel {
    private static EntityManagerFactory  factory = Persistence .createEntityManagerFactory("lab_jpa");
    private static EntityManager  manager = factory.createEntityManager();
    private static AluguelRepository  aluguelRepository = new AluguelRepository(manager);
    private static AluguelService  aluguelService = new AluguelService(manager);

    public static void main(String[] args) {
        // OBSERVAÇÃO: Antes de Realizar os Seguintes Testes Crie uma Locação na
        // respectiva Classe de Testes
        try {
            testCreate(1);
//            testRead(1);
//            testUpdate(1);
//            testDelete(1);
//            testFindImoveisDisponiveisComValorInferiorOuIgual(1500.00);
//            testFindAlugueisPorNomeCliente("João");
//            testFindAlugueisPagosComAtraso();
//            testRegistrarPagamento(1);
        } finally {
            manager.close();
            factory.close();
        }
    }
    private static void testCreate(int idLocacao) {
        System.out.println("Test Create");
        EntityTransaction  transaction = manager.getTransaction();
        try {
            transaction.begin();

            Locacao  locacao = manager.find(Locacao.class, idLocacao); // Substitua pelo ID correto
            Aluguel  aluguel = new Aluguel();
            aluguel.setLocacao(locacao);
            aluguel.setDataVencimento(new Date() );
            aluguel.setValorPago(1500.00);
            aluguel.setDataPagamento(new Date());
            aluguel.setObs("Pagamento em dia");

            aluguel = aluguelRepository.salvaOuAtualiza(aluguel);
            System.out.println("Aluguel criado com ID: " + aluguel.getId());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    private static void testRead(int idAluguel) {
        System.out.println("Test Read");
        Integer id = idAluguel; // Substitua pelo ID correto que você espera que exista
        Aluguel aluguel = aluguelRepository.buscaPorId(Aluguel.class, id);
        if (aluguel != null) {
            System.out.println("Aluguel encontrado com ID: " + aluguel.getId());
        } else {
            System.out.println("Aluguel não encontrado com ID: " + id);
        }
    }

    private static void testUpdate(int idAluguel) {
        System.out.println("Test Update");
        Integer id = idAluguel; // Substitua pelo ID correto que você espera que exista
        Aluguel aluguel = aluguelRepository.buscaPorId(Aluguel.class, id);
        if (aluguel != null) {
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();

                aluguel.setValorPago(1600.00);
                aluguel.setObs("Valor atualizado");
                aluguel = aluguelRepository.salvaOuAtualiza(aluguel);

                transaction.commit();
                System.out.println("Aluguel atualizado com ID: " + aluguel.getId());
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Aluguel não encontrado com ID: " + id);
        }
    }

    private static void testDelete(int idAluguel) {
        System.out.println("Test Delete");
        Integer id = idAluguel; // Substitua pelo ID correto que você espera que exista
        Aluguel aluguel = aluguelRepository.buscaPorId(Aluguel.class, id);
        if (aluguel != null) {
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();

                aluguelRepository.remove(aluguel);

                transaction.commit();
                System.out.println("Aluguel deletado com ID: " + id);
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Aluguel não encontrado com ID: " + id);
        }
    }

    private static void testFindImoveisDisponiveisComValorInferiorOuIgual(Double valorLimiar) {
        System.out.println("Test Find Imoveis Disponiveis Com Valor Inferior Ou Igual");
        List <Imovel> imoveis = aluguelRepository.findImoveisDisponiveisComValorInferiorOuIgual(valorLimiar);
        for (Imovel imovel : imoveis) {
            System.out.println("Imovel encontrado: " + imovel.getLogradouro());
        }
    }

    private static void testFindAlugueisPorNomeCliente(String nomeCliente) {
        System.out.println("Test Find Alugueis Por Nome Cliente");
        List<Aluguel> alugueis = aluguelRepository.findAlugueisPorNomeCliente(nomeCliente);
        for (Aluguel aluguel : alugueis) {
            System.out.println("Aluguel encontrado apra cliente: " + aluguel.getLocacao().getInquilino().getNome());
        }
    }

    private static void testFindAlugueisPagosComAtraso() {
        System.out.println("Test Find Alugueis Pagos Com Atraso");
        List<Aluguel> alugueis = aluguelRepository.findAlugueisPagosComAtraso();
        for (Aluguel aluguel : alugueis) {
            System.out.println("Aluguel pago com atraso: " + aluguel.getId());
        }
    }

    private static void testRegistrarPagamento(int idAluguel) {
        System.out.println("Test Registrar Pagamento");
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();

            Integer aluguelId = idAluguel; // Substitua pelo ID correto que você espera que exista
            Date dataPagamento = new Date();
            Aluguel aluguel = aluguelService.registrarPagamento(aluguelId, dataPagamento);
            System.out.println("Pagamento registrado para Aluguel ID: " + aluguel.getId());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }




}
