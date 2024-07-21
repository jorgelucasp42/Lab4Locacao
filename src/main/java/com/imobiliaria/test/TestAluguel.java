package com.imobiliaria.test;

import com.imobiliaria.model.Aluguel;
import com.imobiliaria.model.Locacao;
import com.imobiliaria.repository.AluguelRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.Date;

public class TestAluguel {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        AluguelRepository aluguelRepository = new AluguelRepository(em);

        Locacao locacao = new Locacao();
        locacao.setId(1); // Supondo que a locação com ID 1 já exista

        Aluguel aluguel = new Aluguel();
        aluguel.setLocacao(locacao);
        aluguel.setDataVencimento(new Date());
        aluguel.setValorPago(1000.0);
        aluguel.setDataPagamento(new Date());

        em.getTransaction().begin();
        aluguelRepository.salvaOuAtualiza(aluguel);
        em.getTransaction().commit();

        Aluguel aluguelEncontrado = aluguelRepository.buscaPorId(aluguel.getId());
        System.out.println("Aluguel encontrado: " + aluguelEncontrado.getId());

        em.close();
    }
}
