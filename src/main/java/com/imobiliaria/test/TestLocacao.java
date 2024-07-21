package com.imobiliaria.test;

import com.imobiliaria.model.Locacao;
import com.imobiliaria.model.Imovel;
import com.imobiliaria.model.Cliente;
import com.imobiliaria.repository.LocacaoRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.Date;

public class TestLocacao {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        LocacaoRepository locacaoRepository = new LocacaoRepository(em);

        Imovel imovel = new Imovel();
        imovel.setId(1); // Supondo que o imóvel com ID 1 já exista

        Cliente inquilino = new Cliente();
        inquilino.setId(1); // Supondo que o cliente com ID 1 já exista

        Locacao locacao = new Locacao();
        locacao.setImovel(imovel);
        locacao.setInquilino(inquilino);
        locacao.setValorAluguel(1500.0);
        locacao.setPercentualMulta(0.33);
        locacao.setDiaVencimento((byte) 5);
        locacao.setDataInicio(new Date());
        locacao.setDataFim(new Date());
        locacao.setAtivo(true);

        em.getTransaction().begin();
        locacaoRepository.salvaOuAtualiza(locacao);
        em.getTransaction().commit();

        Locacao locacaoEncontrada = locacaoRepository.buscaPorId(locacao.getId());
        System.out.println("Locação encontrada: " + locacaoEncontrada.getId());

        em.close();
    }
}
