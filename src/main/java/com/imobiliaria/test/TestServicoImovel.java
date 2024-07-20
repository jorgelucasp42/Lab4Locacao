package com.imobiliaria.test;

import com.imobiliaria.model.Profissional;
import com.imobiliaria.model.ServicoImovel;
import com.imobiliaria.model.Imovel;
import com.imobiliaria.repository.ImovelRepository;
import com.imobiliaria.repository.ProfissionalRepository;
import com.imobiliaria.repository.ServicoImovelRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class TestServicoImovel {

    public static void main(String[] args) {
        EntityManager manager = JPAUtil.getEntityManager();
        ServicoImovelRepository servicoImovelRepository = new ServicoImovelRepository(manager);
        ImovelRepository imovelRepository = new ImovelRepository(manager);
        ProfissionalRepository profissionalRepository = new ProfissionalRepository(manager);

        // Criar profissional e imóvel para o serviço
        Profissional profissional = new Profissional();
        profissional.setNome("Bruno Lima");
        profissional.setProfissao("Pintor");
        profissional.setTelefone1("999765432");
        profissional.setValorHora(30.00);
        profissional.setObs("Profissional com 3 anos de experiência.");

        Imovel imovel = new Imovel();
        imovel.setIdProprietario(1);
        imovel.setLogradouro("Rua D");
        imovel.setBairro("Centro");
        imovel.setCep("44556677");
        imovel.setMetragem(90);
        imovel.setDormitorios((byte) 2);
        imovel.setBanheiros((byte) 1);
        imovel.setSuites((byte) 1);
        imovel.setVagasGaragem((byte) 1);
        imovel.setValorAluguelSugerido(1300.00);
        imovel.setObs("Imóvel reformado.");

        manager.getTransaction().begin();
        profissional = profissionalRepository.salvaOuAtualiza(profissional);
        imovel = imovelRepository.salvaOuAtualiza(imovel);
        manager.getTransaction().commit();

        // Criar um novo serviço
        ServicoImovel servicoImovel = new ServicoImovel();
        servicoImovel.setProfissional(profissional);
        servicoImovel.setImovel(imovel);
        servicoImovel.setDataServico(new Date());
        servicoImovel.setValorTotal(500.00);
        servicoImovel.setObs("Pintura externa.");

        manager.getTransaction().begin();
        ServicoImovel savedServicoImovel = servicoImovelRepository.salvaOuAtualiza(servicoImovel);
        manager.getTransaction().commit();
        System.out.println("Serviço salvo: " + savedServicoImovel);

        // Buscar serviço por ID
        ServicoImovel foundServicoImovel = servicoImovelRepository.buscaPor(savedServicoImovel.getId());
        System.out.println("Serviço encontrado: " + foundServicoImovel);

        // Atualizar serviço
        savedServicoImovel.setValorTotal(550.00);
        manager.getTransaction().begin();
        ServicoImovel updatedServicoImovel = servicoImovelRepository.salvaOuAtualiza(savedServicoImovel);
        manager.getTransaction().commit();
        System.out.println("Serviço atualizado: " + updatedServicoImovel);

        // Listar todos os serviços
        List<ServicoImovel> servicos = servicoImovelRepository.buscaTodos();
        servicos.forEach(s -> System.out.println("Serviço: " + s));

        // Remover serviço
        manager.getTransaction().begin();
        servicoImovelRepository.remove(updatedServicoImovel);
        manager.getTransaction().commit();
        System.out.println("Serviço removido");

        manager.close();
    }
}
