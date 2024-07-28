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
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
    private static EntityManager manager = factory.createEntityManager();
    private static ServicoImovelRepository servicoImovelRepository = new ServicoImovelRepository(manager);
    private static ImovelRepository imovelRepository = new ImovelRepository(manager);
    private static ProfissionalRepository profissionalRepository = new ProfissionalRepository(manager);

    public static void main(String[] args) {
        // OBSERVAÇÃO: Execute antes a classe PopulaBanco
        try {
            System.out.println("========== Teste de Criação de Serviços de Imóvel ==========");
            criarServicoImovel(1, 1);

            System.out.println("========== Teste de Leitura de Serviço de Imóvel ==========");
            lerServicoImovel(1);

            System.out.println("========== Teste de Atualização de Serviço de Imóvel ==========");
            atualizarServicoImovel(1);

            System.out.println("========== Teste de Remoção de Serviço de Imóvel ==========");
            removerServicoImovel(1);
        } finally {
            manager.close();
            factory.close();
        }
    }

    private static ServicoImovel criarServicoImovelConstrutor(Imovel imovel, Profissional profissional) {
        return new ServicoImovel(null, profissional, imovel, new Date(), 500.00, "Reparo sala de estar");
    }

    private static void criarServicoImovel(int idImovel, int idProfissional) {
        EntityTransaction transacao = manager.getTransaction();
        try {
            transacao.begin();

            Imovel imovel = imovelRepository.buscaPorId(Imovel.class, idImovel);
            Profissional profissional = profissionalRepository.buscaPorId(Profissional.class, idProfissional);

            if (imovel == null) {
                System.out.println("Imóvel não encontrado com ID: " + idImovel);
                return;
            }

            if (profissional == null) {
                System.out.println("Profissional não encontrado com ID: " + idProfissional);
                return;
            }

            ServicoImovel servicoImovel = criarServicoImovelConstrutor(imovel, profissional);
            servicoImovel = servicoImovelRepository.salvaOuAtualiza(servicoImovel);

            transacao.commit();
            imprimirServicoImovel(servicoImovel);
            System.out.println("Serviço de Imóvel criado com sucesso.");

        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        }
    }

    private static void lerServicoImovel(int idServico) {
        ServicoImovel servicoImovel = servicoImovelRepository.buscaPorId(ServicoImovel.class, idServico);
        if (servicoImovel != null) {
            System.out.println("Serviço de Imóvel encontrado:");
            imprimirServicoImovel(servicoImovel);
        } else {
            System.out.println("Serviço de Imóvel não encontrado com ID: " + idServico);
        }
    }

    private static void atualizarServicoImovel(int idServico) {
        ServicoImovel servicoImovel = servicoImovelRepository.buscaPorId(ServicoImovel.class, idServico);
        if (servicoImovel != null) {
            EntityTransaction transacao = manager.getTransaction();
            try {
                transacao.begin();

                servicoImovel.setValorTotal(600.00);
                servicoImovel.setObs("Atualização de observação");
                servicoImovel = servicoImovelRepository.salvaOuAtualiza(servicoImovel);

                transacao.commit();
                imprimirServicoImovel(servicoImovel);
                System.out.println("Serviço de Imóvel atualizado com sucesso.");

            } catch (Exception e) {
                transacao.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Serviço de Imóvel não encontrado com ID: " + idServico);
        }
    }

    private static void removerServicoImovel(int idServico) {
        ServicoImovel servicoImovel = servicoImovelRepository.buscaPorId(ServicoImovel.class, idServico);
        if (servicoImovel != null) {
            EntityTransaction transacao = manager.getTransaction();
            try {
                transacao.begin();

                servicoImovelRepository.remove(servicoImovel);
                transacao.commit();
                System.out.println("Serviço de Imóvel removido com sucesso.");
            } catch (Exception e) {
                transacao.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Serviço de Imóvel não encontrado com ID: " + idServico);
        }
    }

    private static void imprimirServicoImovel(ServicoImovel servicoImovel) {
        System.out.println("ID: " + servicoImovel.getId());
        System.out.println("Profissional: " + servicoImovel.getProfissional().getNome());
        System.out.println("Imóvel: " + servicoImovel.getImovel().getLogradouro());
        System.out.println("Data do Serviço: " + servicoImovel.getDataServico());
        System.out.println("Valor Total: " + servicoImovel.getValorTotal());
        System.out.println("Observações: " + servicoImovel.getObs());
        System.out.println("=====================================");
    }
}
