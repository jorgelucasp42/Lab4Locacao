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

public class TesteImovel {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
    private static EntityManager manager = factory.createEntityManager();
    private static ImovelRepository imovelRepository = new ImovelRepository(manager);
    private static ClienteRepository clienteRepository = new ClienteRepository(manager);
    private static TipoImovelRepository  tipoImovelRepository = new TipoImovelRepository(manager);

    public static void main(String[] args) {
        // OBSERVAÇÃO: Antes de Realizar os Seguintes Testes Crie um Cliente e Um TipoImóvel nas
        // respectivas Classes de Testes
        try {
            testCreate(1,1); // Para criar diferentes imóveis altere os campos no método getImovel
//            testRead(1);
//            testUpdate(1);
//            testDelete(2);
        } finally {
            manager.close();
            factory.close();
        }
    }
    private static @NotNull Imovel getImovel(TipoImovel tipoImovel, Cliente cliente) {
        Imovel  imovel = new Imovel();
        imovel.setTipoImovel(tipoImovel);
        imovel.setProprietario(cliente);
        imovel.setLogradouro("Rua Exemplo 2");
        imovel.setBairro("Bairro Exemplo 2");
        imovel.setCep("12345-679");
        imovel.setMetragem(100);
        imovel.setDormitorios((byte) 2);
        imovel.setBanheiros((byte) 1);
        imovel.setSuites((byte) 1);
        imovel.setVagasGaragem((byte) 1);
        imovel.setValorAluguelSugerido(1500.00);
        imovel.setObs("Observação exemplo 2");
        return imovel;
    }

    private static void testCreate(int idCliente, int idTipoImovel) {
        System.out.println("Test Create");
        EntityTransaction  transaction = manager.getTransaction();
        try {
            transaction.begin();

            Cliente  cliente = clienteRepository.buscaPorId(Cliente.class, idCliente); // Substitua pelo ID correto
            TipoImovel  tipoImovel = tipoImovelRepository.buscaPorId(TipoImovel.class, idTipoImovel); // Substitua pelo ID correto

            Imovel imovel = getImovel(tipoImovel, cliente);

            imovel = imovelRepository.salvaOuAtualiza(imovel);

            transaction.commit();
            System.out.println("Imovel cirado com ID: " + imovel.getId());
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }


    private static void testRead(int idImovel) {
        System.out.println("Test Read");
        Integer id = idImovel; // Substitua pelo ID correto que você espera que exista
        Imovel imovel = imovelRepository.buscaPorId(Imovel.class, id);
        if (imovel != null) {
            System.out.println("Imovel encontrado: " + imovel.getLogradouro() + ", " + imovel.getBairro());
        } else {
            System.out.println("Imovel não encontrado com ID: " + id);
        }
    }

    private static void testUpdate(int idImovel) {
        System.out.println("Test Update");
        Integer id = idImovel; // Substitua pelo ID correto que você espera que exista
        Imovel imovel = imovelRepository.buscaPorId(Imovel.class, id);
        if (imovel != null) {
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();

                imovel.setLogradouro("Rua Atualizada");
                imovel.setValorAluguelSugerido(1800.00);
                imovel = imovelRepository.salvaOuAtualiza(imovel);

                transaction.commit();
                System.out.println("Imovel atualizado com ID: " + imovel.getId());
                System.out.println(imovel.toString());

            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Imovel não encontrado com ID: " + id);
        }
    }

    private static void testDelete(int idImovel) {
        System.out.println("Test Delete");
        Integer id = idImovel; // Substitua pelo ID correto que você espera que exista
        Imovel imovel = imovelRepository.buscaPorId(Imovel.class, id);
        if (imovel != null) {
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();

                imovelRepository.remove(imovel);
                System.out.println("Imovel deletado com ID: " + id);

                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } else {
            System.out.println("Imovel não encontrado com ID: " + id);
        }
    }
}
