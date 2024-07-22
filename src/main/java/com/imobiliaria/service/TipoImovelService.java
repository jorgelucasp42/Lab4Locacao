package com.imobiliaria.service;

import com.imobiliaria.repository.TipoImovelRepository;

import javax.persistence.EntityManager;

public class TipoImovelService {
    private final TipoImovelRepository tipoImovelRepository;

    public TipoImovelService(EntityManager em) {
        this.tipoImovelRepository = new TipoImovelRepository(em);
    }

}
