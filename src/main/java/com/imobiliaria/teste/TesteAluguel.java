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
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
    private static EntityManager manager = factory.createEntityManager();
    private static AluguelRepository aluguelRepository = new AluguelRepository(manager);
    private static AluguelService aluguelService = new AluguelService(manager);

    public static void main(String[] args) {
        // OBSERVAÇÃO: Antes de testar esta classe, execute a Classe TesteLocacao
        try {
            System.out.println("========== Teste de Criação de Aluguel ==========");
            criarAluguel(1);

            System.out.println("========== Teste de Criação de Aluguel com Atraso ==========");
            criarAluguelComAtraso(2);

            System.out.println("========== Teste de Leitura de Aluguel ==========");
            lerAluguel(1);

            System.out.println("========== Teste de Atualização de Aluguel ==========");
            atualizarAluguel(1);

            System.out.println("========== Teste de Remoção de Aluguel ==========");
            removerAluguel(1);

            System.out.println("========== Teste de Imóveis Disponíveis com Valor Inferior ou Igual ==========");
            findImoveisDisponiveisComValorInferiorOuIgual(2500.00);

            System.out.println("========== Teste de Aluguéis por Nome do Cliente ==========");
            findAlugueisPorNomeCliente("Alice");

            System.out.println("========== Teste de Aluguéis Pagos com Atraso ==========");
            findAlugueisPagosComAtraso();

            System.out.println("========== Teste de Registro de Pagamento ==========");
           registrarPagamento(2);
        } finally {
            manager.close();
            factory.close();
        }
    }

    private static Aluguel criarAluguelConstrutor(Locacao locacao) {
        return new Aluguel(null, locacao, new Date(), 1500.00, new Date(), "Pagamento em dia");
    }

    private static Aluguel criarAluguelComAtrasoConstrutor(Locacao locacao) {
        Date dataVencimento = new Date(System.currentTimeMillis() - 86400000L * 10); // Data de vencimento 10 dias atrás
        Date dataPagamento = new Date(); // Data de pagamento atual
        return new Aluguel(null, locacao, dataVencimento, 1600.00, dataPagamento, "Pagamento com atraso");
    }

    private static void criarAluguel(int idLocacao) {
        EntityTransaction transacao = manager.getTransaction();
        try {
            transacao.begin();

            Locacao locacao = manager.find(Locacao.class, idLocacao);

            if (locacao == null) {
                System.out.println("Locação não encontrada com ID: " + idLocacao);
                return;
            }

            Aluguel aluguel = criarAluguelConstrutor(locacao);
            aluguel = aluguelRepository.salvaOuAtualiza(aluguel);

            transacao.commit();
            imprimirAluguel(aluguel);
            System.out.println("Aluguel criado com sucesso.");
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        }
    }

    private static void criarAluguelComAtraso(int idLocacao) {
        EntityTransaction transacao = manager.getTransaction();
        try {
            transacao.begin();

            Locacao locacao = manager.find(Locacao.class, idLocacao);

            if (locacao == null) {
                System.out.println("Locação não encontrada com ID: " + idLocacao);
                return;
            }

            Aluguel aluguel = criarAluguelComAtrasoConstrutor(locacao);
            aluguel = aluguelRepository.salvaOuAtualiza(aluguel);

            transacao.commit();
            imprimirAluguel(aluguel);
            System.out.println("Aluguel criado com atraso com sucesso.");
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        }
    }

    private static void lerAluguel(int idAluguel) {
        Aluguel aluguel = aluguelRepository.buscaPorId(Aluguel.class, idAluguel);
        if (aluguel != null) {
            System.out.println("Aluguel encontrado:");
            imprimirAluguel(aluguel);
        } else {
            System.out.println("Aluguel não encontrado com ID: " + idAluguel);
        }
    }

    private static void atualizarAluguel(int idAluguel) {
        Aluguel aluguel = aluguelRepository.buscaPorId(Aluguel.class, idAluguel);
        if (aluguel != null) {
            EntityTransaction transacao = manager.getTransaction();
            try {
                transacao.begin();

                aluguel.setValorPago(1600.00);
                aluguel.setObs("Valor atualizado");
                aluguel = aluguelRepository.salvaOuAtualiza(aluguel);

                transacao.commit();
                imprimirAluguel(aluguel);
                System.out.println("Aluguel atualizado com sucesso.");
            } catch (Exception e) {
                transacao.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Aluguel não encontrado com ID: " + idAluguel);
        }
    }

    private static void removerAluguel(int idAluguel) {
        Aluguel aluguel = aluguelRepository.buscaPorId(Aluguel.class, idAluguel);
        if (aluguel != null) {
            EntityTransaction transacao = manager.getTransaction();
            try {
                transacao.begin();

                aluguelRepository.remove(aluguel);

                transacao.commit();
                System.out.println("Aluguel removido com sucesso.");
            } catch (Exception e) {
                transacao.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Aluguel não encontrado com ID: " + idAluguel);
        }
    }

    private static void findImoveisDisponiveisComValorInferiorOuIgual(Double valorLimiar) {
        List<Imovel> imoveis = aluguelRepository.findImoveisDisponiveisComValorInferiorOuIgual(valorLimiar);
        for (Imovel imovel : imoveis) {
            System.out.println("Imóvel encontrado: " + imovel.getLogradouro() + " com valor de aluguel " + imovel.getValorAluguelSugerido());
        }
    }

    private static void findAlugueisPorNomeCliente(String nomeCliente) {
        List<Aluguel> alugueis = aluguelRepository.findAlugueisPorNomeCliente(nomeCliente);
        for (Aluguel aluguel : alugueis) {
            System.out.println("Aluguel encontrado para cliente: " + aluguel.getLocacao().getInquilino().getNome() + " com ID de Aluguel: " + aluguel.getId());
        }
    }

    private static void findAlugueisPagosComAtraso() {
        List<Aluguel> alugueis = aluguelRepository.findAlugueisPagosComAtraso();
        for (Aluguel aluguel : alugueis) {
            System.out.println("Aluguel pago com atraso: ID-" + aluguel.getId());
        }
    }

    private static void registrarPagamento(int idAluguel) {
        EntityTransaction transacao = manager.getTransaction();
        try {
            transacao.begin();

            Integer aluguelId = idAluguel;
            Date dataPagamento = new Date();
            Aluguel aluguel = aluguelService.registrarPagamento(aluguelId, dataPagamento);
            System.out.println("Pagamento registrado para Aluguel ID: " + aluguel.getId());

            transacao.commit();
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        }
    }

    private static void imprimirAluguel(Aluguel aluguel) {
        System.out.println("ID: " + aluguel.getId());
        System.out.println("Locação ID: " + aluguel.getLocacao().getId());
        System.out.println("Imóvel: " + aluguel.getLocacao().getImovel().getLogradouro());
        System.out.println("Inquilino: " + aluguel.getLocacao().getInquilino().getNome());
        System.out.println("Data de Vencimento: " + aluguel.getDataVencimento());
        System.out.println("Valor Pago: " + aluguel.getValorPago());
        System.out.println("Data de Pagamento: " + aluguel.getDataPagamento());
        System.out.println("Observações: " + aluguel.getObs());
        System.out.println("=====================================");
    }
}
