package com.imobiliaria.test;

import com.imobiliaria.model.Cliente;
import com.imobiliaria.model.Imovel;
import com.imobiliaria.model.Locacao;
import com.imobiliaria.repository.ClienteRepository;
import com.imobiliaria.repository.ImovelRepository;
import com.imobiliaria.repository.LocacaoRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class TestLocacao {

    public static void main(String[] args) {
        EntityManager manager = JPAUtil.getEntityManager();
        LocacaoRepository locacaoRepository = new LocacaoRepository(manager);
        ImovelRepository imovelRepository = new ImovelRepository(manager);
        ClienteRepository clienteRepository = new ClienteRepository(manager);

        // Criar cliente e imóvel para a locação
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Souza");
        cliente.setCpf("98765432100");
        cliente.setTelefone("999888777");
        cliente.setEmail("maria.souza@example.com");
        cliente.setDtNascimento(new java.util.Date());

        Imovel imovel = new Imovel();
        imovel.setIdProprietario(1);
        imovel.setLogradouro("Rua B");
        imovel.setBairro("Centro");
        imovel.setCep("87654321");
        imovel.setMetragem(80);
        imovel.setDormitorios((byte) 2);
        imovel.setBanheiros((byte) 1);
        imovel.setSuites((byte) 1);
        imovel.setVagasGaragem((byte) 1);
        imovel.setValorAluguelSugerido(1200.00);
        imovel.setObs("Imóvel novo.");

        manager.getTransaction().begin();
        cliente = clienteRepository.salvaOuAtualiza(cliente);
        imovel = imovelRepository.salvaOuAtualiza(imovel);
        manager.getTransaction().commit();

        // Criar uma nova locação
        Locacao locacao = new Locacao();
        locacao.setImovel(imovel);
        locacao.setInquilino(cliente);
        locacao.setValorAluguel(1200.00);
        locacao.setPercentualMulta(0.33);
        locacao.setDiaVencimento((byte) 5);
        locacao.setDataInicio(new Date());
        locacao.setAtivo(true);

        manager.getTransaction().begin();
        Locacao savedLocacao = locacaoRepository.salvaOuAtualiza(locacao);
        manager.getTransaction().commit();
        System.out.println("Locação salva: " + savedLocacao);

        // Buscar locação por ID
        Locacao foundLocacao = locacaoRepository.buscaPor(savedLocacao.getId());
        System.out.println("Locação encontrada: " + foundLocacao);

        // Atualizar locação
        savedLocacao.setValorAluguel(1300.00);
        manager.getTransaction().begin();
        Locacao updatedLocacao = locacaoRepository.salvaOuAtualiza(savedLocacao);
        manager.getTransaction().commit();
        System.out.println("Locação atualizada: " + updatedLocacao);

        // Listar todas as locações
        List<Locacao> locacoes = locacaoRepository.buscaTodos();
        locacoes.forEach(l -> System.out.println("Locação: " + l));

        // Remover locação
        manager.getTransaction().begin();
        locacaoRepository.remove(updatedLocacao);
        manager.getTransaction().commit();
        System.out.println("Locação removida");

        manager.close();
    }
}
