package com.imobiliaria.test;

import com.imobiliaria.model.Imovel;
import com.imobiliaria.model.TipoImovel;
import com.imobiliaria.repository.ImovelRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;

public class TestImovel {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        ImovelRepository imovelRepository = new ImovelRepository(em);

        TipoImovel tipoImovel = new TipoImovel();
        tipoImovel.setId(1); // Supondo que o tipo de imóvel com ID 1 já exista

        Imovel imovel = new Imovel();
        imovel.setTipoImovel(tipoImovel);
        imovel.setLogradouro("Rua das Flores");
        imovel.setBairro("Centro");
        imovel.setCep("12345678");
        imovel.setMetragem(100);
        imovel.setDormitorios(3);
        imovel.setBanheiros(2);
        imovel.setSuites(1);
        imovel.setVagasGaragem(2);
        imovel.setValorAluguelSugerido(1500.0);
        imovel.setAtivo(true);

        em.getTransaction().begin();
        imovelRepository.salvaOuAtualiza(imovel);
        em.getTransaction().commit();

        Imovel imovelEncontrado = imovelRepository.buscaPorId(imovel.getId());
        System.out.println("Imóvel encontrado: " + imovelEncontrado.getLogradouro());

        em.close();
    }
}
