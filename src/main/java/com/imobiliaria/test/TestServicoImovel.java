package com.imobiliaria.test;

import com.imobiliaria.model.ServicoImovel;
import com.imobiliaria.model.Profissional;
import com.imobiliaria.model.Imovel;
import com.imobiliaria.repository.ServicoImovelRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.Date;

public class TestServicoImovel {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        ServicoImovelRepository servicoImovelRepository = new ServicoImovelRepository(em);

        Profissional profissional = new Profissional();
        profissional.setId(1); // Supondo que o profissional com ID 1 já exista

        Imovel imovel = new Imovel();
        imovel.setId(1); // Supondo que o imóvel com ID 1 já exista

        ServicoImovel servicoImovel = new ServicoImovel();
        servicoImovel.setProfissional(profissional);
        servicoImovel.setImovel(imovel);
        servicoImovel.setDataServico(new Date());
        servicoImovel.setValorTotal(500.0);

        em.getTransaction().begin();
        servicoImovelRepository.salvaOuAtualiza(servicoImovel);
        em.getTransaction().commit();

        ServicoImovel servicoImovelEncontrado = servicoImovelRepository.buscaPorId(servicoImovel.getId());
        System.out.println("Serviço de Imóvel encontrado: " + servicoImovelEncontrado.getId());

        em.close();
    }
}
