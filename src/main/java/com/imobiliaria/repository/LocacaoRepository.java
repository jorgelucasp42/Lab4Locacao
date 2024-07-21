package com.imobiliaria.repository;

import com.imobiliaria.model.Locacao;

import javax.persistence.EntityManager;

public class LocacaoRepository extends DAOGenerico<Locacao> {
    public LocacaoRepository(EntityManager em) {
        super(Locacao.class, em);
    }
}
