package com.imobiliaria.repository;

import com.imobiliaria.model.Locacao;

import javax.persistence.EntityManager;
import java.util.List;

public class LocacaoRepository extends DAOGenerico<Locacao> {
    public LocacaoRepository(EntityManager manager) {
        super(manager);
    }

    public List<Locacao> findAll() {
        return super.findAll(Locacao.class);
    }
}
