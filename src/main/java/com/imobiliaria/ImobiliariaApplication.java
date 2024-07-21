package com.imobiliaria;

import com.imobiliaria.model.Imovel;
import com.imobiliaria.repository.ImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager em = emf.createEntityManager();

        ImovelRepository imovelRepository = new ImovelRepository(em);

        // Exemplo de uso
        Imovel imovel = new Imovel();
        imovel.setLogradouro("Rua A");
        imovel.setBairro("Centro");
        imovel.setCep("12345-678");
        imovel.setMetragem(100);
        imovel.setDormitorios((byte) 2);
        imovel.setBanheiros((byte) 1);
        imovel.setSuites((byte) 1);
        imovel.setVagasGaragem((byte) 1);
        imovel.setValorAluguelSugerido(1500.00);

        imovelRepository.salvaOuAtualiza(imovel);

        imovelRepository.findAll().forEach(System.out::println);

        em.close();
        emf.close();
    }
}
