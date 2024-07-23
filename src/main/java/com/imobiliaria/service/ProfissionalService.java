package com.imobiliaria.service;

import com.imobiliaria.repository.ProfissionalRepository;

import javax.persistence.EntityManager;

public class ProfissionalService {
    private final ProfissionalRepository profissionalRepository;

    public ProfissionalService(EntityManager em) {
        this.profissionalRepository = new ProfissionalRepository(em);
    }
}
