package com.imobiliaria.service;

import com.imobiliaria.repository.ServicoImovelRepository;

import javax.persistence.EntityManager;

public class ServicoImovelService {
    private final ServicoImovelRepository servicoImovelRepository;

    public ServicoImovelService(EntityManager em) {
        this.servicoImovelRepository = new ServicoImovelRepository(em);
    }

}
