package com.imobiliaria.test;

import com.imobiliaria.model.Aluguel;
import com.imobiliaria.model.Cliente;
import com.imobiliaria.model.Imovel;
import com.imobiliaria.model.Locacao;
import com.imobiliaria.repository.AluguelRepository;
import com.imobiliaria.repository.ClienteRepository;
import com.imobiliaria.repository.ImovelRepository;
import com.imobiliaria.repository.LocacaoRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class TestAluguel {

    public static void main(String[] args) {
        EntityManager manager = JPAUtil.getEntityManager();
        AluguelRepository aluguelRepository = new AluguelRepository(manager);
        LocacaoRepository locacaoRepository = new LocacaoRepository(manager);
        ImovelRepository imovelRepository = new ImovelRepository(manager);
        ClienteRepository clienteRepository = new ClienteRepository(manager);

        // Criar cliente, imóvel e locação para o aluguel
        Cliente cliente = new Cliente();
        cliente.setNome("Carlos Pereira");
        cliente.setCpf("11223344556");
        cliente.setTelefone("999111222");
        cliente.setEmail("carlos.pereira@example.com");
        cliente.setDtNascimento(new java.util.Date());

        Imovel imovel = new Imovel();
        imovel.setIdProprietario(1);
        imovel.setLogradouro("Rua C");
        imovel.setBairro("Centro");
        imovel.setCep("11223344");
        imovel.setMetragem(120);
        imovel.setDormitorios((byte) 3);
        imovel.setBanheiros((byte) 2);
        imovel.setSuites((byte) 1);
        imovel.setVagasGaragem((byte) 2);
        imovel.setValorAluguelSugerido(2000.00);
        imovel.setObs("Imóvel espaçoso.");

        manager.getTransaction().begin();
        cliente = clienteRepository.salvaOuAtualiza(cliente);
        imovel = imovelRepository.salvaOuAtualiza(imovel);
        manager.getTransaction().commit();

        Locacao locacao = new Locacao();
        locacao.setImovel(imovel);
        locacao.setInquilino(cliente);
        locacao.setValorAluguel(2000.00);
        locacao.setPercentualMulta(0.33);
        locacao.setDiaVencimento((byte) 10);
        locacao.setDataInicio(new Date());
        locacao.setAtivo(true);

        manager.getTransaction().begin();
        locacao = locacaoRepository.salvaOuAtualiza(locacao);
        manager.getTransaction().commit();

        // Criar um novo aluguel
        Aluguel aluguel = new Aluguel();
        aluguel.setLocacao(locacao);
        aluguel.setDataVencimento(new Date());
        aluguel.setValorPago(2000.00);

        manager.getTransaction().begin();
        Aluguel savedAluguel = aluguelRepository.salvaOuAtualiza(aluguel);
        manager.getTransaction().commit();
        System.out.println("Aluguel salvo: " + savedAluguel);

        // Buscar aluguel por ID
        Aluguel foundAluguel = aluguelRepository.buscaPor(savedAluguel.getId());
        System.out.println("Aluguel encontrado: " + foundAluguel);

        // Atualizar aluguel (registrar pagamento)
        Date dataPagamento = new Date();
        double valorComMulta = aluguelRepository.calcularValorComMulta(foundAluguel, dataPagamento);

        foundAluguel.setDataPagamento(dataPagamento);
        foundAluguel.setValorPago(valorComMulta);

        manager.getTransaction().begin();
        Aluguel updatedAluguel = aluguelRepository.salvaOuAtualiza(foundAluguel);
        manager.getTransaction().commit();
        System.out.println("Aluguel atualizado (pagamento registrado): " + updatedAluguel);

        // Listar todos os aluguéis
        List<Aluguel> aluguels = aluguelRepository.buscaTodos();
        aluguels.forEach(a -> System.out.println("Aluguel: " + a));

        // Remover aluguel
        manager.getTransaction().begin();
        aluguelRepository.remove(updatedAluguel);
        manager.getTransaction().commit();
        System.out.println("Aluguel removido");

        manager.close();
    }
}
