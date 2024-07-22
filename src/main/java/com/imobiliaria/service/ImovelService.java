package com.imobiliaria.service;

import com.imobiliaria.repository.ImovelRepository;

import javax.persistence.EntityManager;

public class ImovelService {
    private final ImovelRepository imovelRepository;

    public ImovelService(EntityManager em) {
        this.imovelRepository = new ImovelRepository(em);
    }

}
