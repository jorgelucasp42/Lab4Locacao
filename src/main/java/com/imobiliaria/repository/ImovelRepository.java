package com.imobiliaria.repository;

import com.imobiliaria.model.Imovel;

import javax.persistence.EntityManager;
import java.util.List;

public class ImovelRepository {

    private final EntityManager manager;
    private DAOGenerico<Imovel> daoGenerico;

    public ImovelRepository(EntityManager manager) {
        this.manager = manager;
        daoGenerico = new DAOGenerico<>(manager);
    }

    public Imovel buscaPor(Integer id) {
        return daoGenerico.buscaPorId(Imovel.class, id);
    }

    public List<Imovel> buscaTodos() {
        return this.manager.createQuery("from Imovel", Imovel.class).getResultList();
    }

    public Imovel salvaOuAtualiza(Imovel imovel) {
        return daoGenerico.salvaOuAtualiza(imovel);
    }

    public void remove(Imovel imovel) {
        daoGenerico.remove(imovel);
    }

    public List<Imovel> buscaPorValorAluguelSugeridoAte(double valor) {
        return this.manager.createQuery("from Imovel where valorAluguelSugerido <= :valor", Imovel.class)
                .setParameter("valor", valor)
                .getResultList();
    }

    public boolean estaDisponivelParaLocacao(Integer idImovel) {
        Long count = this.manager.createQuery("select count(l) from Locacao l where l.imovel.id = :idImovel and l.ativo = true", Long.class)
                .setParameter("idImovel", idImovel)
                .getSingleResult();
        return count == 0;
    }
}

