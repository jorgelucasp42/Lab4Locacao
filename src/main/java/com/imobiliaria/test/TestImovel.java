package com.imobiliaria.test;

import com.imobiliaria.model.Imovel;
import com.imobiliaria.repository.ImovelRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class TestImovel {

    public static void main(String[] args) {
        EntityManager manager = JPAUtil.getEntityManager();
        ImovelRepository imovelRepository = new ImovelRepository(manager);

        // Criar um novo imóvel
        Imovel imovel = new Imovel();
        imovel.setIdProprietario(1);
        imovel.setLogradouro("Rua A");
        imovel.setBairro("Centro");
        imovel.setCep("12345678");
        imovel.setMetragem(100);
        imovel.setDormitorios((byte) 3);
        imovel.setBanheiros((byte) 2);
        imovel.setSuites((byte) 1);
        imovel.setVagasGaragem((byte) 2);
        imovel.setValorAluguelSugerido(1500.00);
        imovel.setObs("Imóvel bem localizado.");

        manager.getTransaction().begin();
        Imovel savedImovel = imovelRepository.salvaOuAtualiza(imovel);
        manager.getTransaction().commit();
        System.out.println("Imóvel salvo: " + savedImovel);

        // Buscar imóvel por ID
        Imovel foundImovel = imovelRepository.buscaPor(savedImovel.getId());
        System.out.println("Imóvel encontrado: " + foundImovel);

        // Atualizar imóvel
        savedImovel.setValorAluguelSugerido(1600.00);
        manager.getTransaction().begin();
        Imovel updatedImovel = imovelRepository.salvaOuAtualiza(savedImovel);
        manager.getTransaction().commit();
        System.out.println("Imóvel atualizado: " + updatedImovel);

        // Listar todos os imóveis
        List<Imovel> imoveis = imovelRepository.buscaTodos();
        imoveis.forEach(i -> System.out.println("Imóvel: " + i));

        // Remover imóvel
        manager.getTransaction().begin();
        imovelRepository.remove(updatedImovel);
        manager.getTransaction().commit();
        System.out.println("Imóvel removido");

        manager.close();
    }
}

