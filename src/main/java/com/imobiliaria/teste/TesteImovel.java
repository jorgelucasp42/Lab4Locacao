package com.imobiliaria.teste;

import com.imobiliaria.model.Cliente;
import com.imobiliaria.model.Imovel;
import com.imobiliaria.model.TipoImovel;
import com.imobiliaria.repository.ClienteRepository;
import com.imobiliaria.repository.ImovelRepository;
import com.imobiliaria.repository.TipoImovelRepository;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class TesteImovel {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
    private static EntityManager manager = factory.createEntityManager();
    private static ImovelRepository imovelRepository = new ImovelRepository(manager);
    private static ClienteRepository clienteRepository = new ClienteRepository(manager);
    private static TipoImovelRepository tipoImovelRepository = new TipoImovelRepository(manager);

    public static void main(String[] args) {
        try {
            System.out.println("========== Teste de Criação de Imóveis ==========");
            criarImovel(1, 1);

            System.out.println("========== Teste de Leitura de Imóvel ==========");
            lerImovel(1);

            System.out.println("========== Teste de Atualização de Imóvel ==========");
            atualizarImovel(1);

            System.out.println("========== Teste de Remoção de Imóvel ==========");
            removerImovel(2);
        } finally {
            manager.close();
            factory.close();
        }
    }

    private static @NotNull Imovel criarImovelConstrutor(TipoImovel tipoImovel, Cliente cliente) {
        return new Imovel(null, tipoImovel, cliente, "Rua Exemplo 2", "Bairro Exemplo 2", "12345-679", 100,
                (byte) 2, (byte) 1, (byte) 1, (byte) 1, 1500.00, "Observação exemplo 2", new ArrayList<>(), new ArrayList<>());
    }

    private static void criarImovel(int idCliente, int idTipoImovel) {
        EntityTransaction transacao = manager.getTransaction();
        try {
            transacao.begin();

            Cliente cliente = clienteRepository.buscaPorId(Cliente.class, idCliente);
            TipoImovel tipoImovel = tipoImovelRepository.buscaPorId(TipoImovel.class, idTipoImovel);

            if (cliente == null) {
                System.out.println("Cliente não encontrado com ID: " + idCliente);
                return;
            }

            if (tipoImovel == null) {
                System.out.println("Tipo de Imóvel não encontrado com ID: " + idTipoImovel);
                return;
            }

            Imovel imovel = criarImovelConstrutor(tipoImovel, cliente);
            imovel = imovelRepository.salvaOuAtualiza(imovel);

            transacao.commit();
            System.out.println("Imóvel criado com sucesso.");
            imprimirImovel(imovel);
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        }
    }

    private static void lerImovel(int idImovel) {
        Imovel imovel = imovelRepository.buscaPorId(Imovel.class, idImovel);
        if (imovel != null) {
            System.out.println("Imóvel encontrado:");
            imprimirImovel(imovel);
        } else {
            System.out.println("Imóvel não encontrado com ID: " + idImovel);
        }
    }

    private static void atualizarImovel(int idImovel) {

        Imovel imovel = imovelRepository.buscaPorId(Imovel.class, idImovel);
        if (imovel != null) {
            EntityTransaction transacao = manager.getTransaction();
            try {
                transacao.begin();

                imovel.setLogradouro("Rua Atualizada");
                imovel.setValorAluguelSugerido(1800.00);
                imovel = imovelRepository.salvaOuAtualiza(imovel);

                transacao.commit();
                System.out.println("Imóvel atualizado com sucesso.");
                imprimirImovel(imovel);
            } catch (Exception e) {
                transacao.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Imóvel não encontrado com ID: " + idImovel);
        }
    }

    private static void removerImovel(int idImovel) {
        Imovel imovel = imovelRepository.buscaPorId(Imovel.class, idImovel);
        if (imovel != null) {
            EntityTransaction transacao = manager.getTransaction();
            try {
                transacao.begin();

                imovelRepository.remove(imovel);
                transacao.commit();
                System.out.println("Imóvel removido com sucesso.");
            } catch (Exception e) {
                transacao.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Imóvel não encontrado com ID: " + idImovel);
        }
    }

    private static void imprimirImovel(Imovel imovel) {
        System.out.println("ID: " + imovel.getId());
        System.out.println("Tipo de Imóvel: " + imovel.getTipoImovel().getDescricao());
        System.out.println("Proprietário: " + imovel.getProprietario().getNome());
        System.out.println("Logradouro: " + imovel.getLogradouro());
        System.out.println("Bairro: " + imovel.getBairro());
        System.out.println("CEP: " + imovel.getCep());
        System.out.println("Metragem: " + imovel.getMetragem());
        System.out.println("Dormitórios: " + imovel.getDormitorios());
        System.out.println("Banheiros: " + imovel.getBanheiros());
        System.out.println("Suítes: " + imovel.getSuites());
        System.out.println("Vagas de Garagem: " + imovel.getVagasGaragem());
        System.out.println("Valor do Aluguel Sugerido: " + imovel.getValorAluguelSugerido());
        System.out.println("Observações: " + imovel.getObs());
        System.out.println("*******************************************");
    }
}
