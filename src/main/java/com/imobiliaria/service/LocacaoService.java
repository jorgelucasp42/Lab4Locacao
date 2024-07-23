package com.imobiliaria.service;

import com.imobiliaria.repository.LocacaoRepository;

import javax.persistence.EntityManager;

public class LocacaoService {
    private final LocacaoRepository locacaoRepository;

    public LocacaoService(EntityManager em) {
        this.locacaoRepository = new LocacaoRepository(em);
    }
}
