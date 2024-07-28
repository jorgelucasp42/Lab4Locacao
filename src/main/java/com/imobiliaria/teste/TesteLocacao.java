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
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
    private static EntityManager manager = factory.createEntityManager();
    private static LocacaoRepository locacaoRepository = new LocacaoRepository(manager);
    private static ImovelRepository imovelRepository = new ImovelRepository(manager);
    private static ClienteRepository clienteRepository = new ClienteRepository(manager);

    public static void main(String[] args) {
        // OBSERVAÇÃO: Execute antes a classe PopulaBanco
        try {
            System.out.println("========== Teste de Criação de Locações ==========");
            criarLocacao(5, 4);

            System.out.println("========== Teste de Leitura de Locação ==========");
            lerLocacao(1);

            System.out.println("========== Teste de Atualização de Locação ==========");
            atualizarLocacao(1);

            System.out.println("========== Teste de Remoção de Locação ==========");
            removerLocacao(5);
        } finally {
            manager.close();
            factory.close();
        }
    }

    private static Locacao criarLocacaoConstrutor(Imovel imovel, Cliente inquilino) {
        return new Locacao(null, imovel, inquilino, 1200.00, 2.0, (byte) 5, new Date(), null, true, "Observação de teste", null);
    }

    private static void criarLocacao(int idCliente, int idImovel) {
        EntityTransaction transacao = manager.getTransaction();
        try {
            transacao.begin();

            Imovel imovel = imovelRepository.buscaPorId(Imovel.class, idImovel);
            Cliente inquilino = clienteRepository.buscaPorId(Cliente.class, idCliente);

            if (imovel == null) {
                System.out.println("Imóvel não encontrado com ID: " + idImovel);
                return;
            }

            if (inquilino == null) {
                System.out.println("Inquilino não encontrado com ID: " + idCliente);
                return;
            }

            Locacao locacao = criarLocacaoConstrutor(imovel, inquilino);
            locacao = locacaoRepository.salvaOuAtualiza(locacao);

            transacao.commit();
            imprimirLocacao(locacao);
            System.out.println("Locação criada com sucesso.");

        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        }
    }

    private static void lerLocacao(int idLocacao) {
        Locacao locacao = locacaoRepository.buscaPorId(Locacao.class, idLocacao);
        if (locacao != null) {
            System.out.println("Locação encontrada:");
            imprimirLocacao(locacao);
        } else {
            System.out.println("Locação não encontrada com ID: " + idLocacao);
        }
    }

    private static void atualizarLocacao(int idLocacao) {
        Locacao locacao = locacaoRepository.buscaPorId(Locacao.class, idLocacao);
        if (locacao != null) {
            EntityTransaction transacao = manager.getTransaction();
            try {
                transacao.begin();

                locacao.setValorAluguel(1300.00);
                locacao.setDiaVencimento((byte) 10);
                locacao.setAtivo(false);
                locacao = locacaoRepository.salvaOuAtualiza(locacao);

                transacao.commit();
                imprimirLocacao(locacao);
                System.out.println("Locação atualizada com sucesso.");

            } catch (Exception e) {
                transacao.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Locação não encontrada com ID: " + idLocacao);
        }
    }

    private static void removerLocacao(int idLocacao) {
        Locacao locacao = locacaoRepository.buscaPorId(Locacao.class, idLocacao);
        if (locacao != null) {
            EntityTransaction transacao = manager.getTransaction();
            try {
                transacao.begin();

                locacaoRepository.remove(locacao);
                transacao.commit();
                System.out.println("Locação removida com sucesso.");
            } catch (Exception e) {
                transacao.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Locação não encontrada com ID: " + idLocacao);
        }
    }

    private static void imprimirLocacao(Locacao locacao) {
        System.out.println("ID: " + locacao.getId());
        System.out.println("Imóvel: " + locacao.getImovel().getLogradouro());
        System.out.println("Inquilino: " + locacao.getInquilino().getNome());
        System.out.println("Valor Aluguel: " + locacao.getValorAluguel());
        System.out.println("Percentual Multa: " + locacao.getPercentualMulta());
        System.out.println("Dia Vencimento: " + locacao.getDiaVencimento());
        System.out.println("Data Início: " + locacao.getDataInicio());
        System.out.println("Data Fim: " + locacao.getDataFim());
        System.out.println("Ativo: " + locacao.getAtivo());
        System.out.println("Observações: " + locacao.getObs());
        System.out.println("Quantidade de Aluguéis: " + (locacao.getAlugueis() != null ? locacao.getAlugueis().size() : 0));
        System.out.println("=====================================");
    }
}
