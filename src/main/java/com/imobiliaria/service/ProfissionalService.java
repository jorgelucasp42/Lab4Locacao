package com.imobiliaria.service;

import com.imobiliaria.model.Profissional;
import com.imobiliaria.repository.ProfissionalRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class ProfissionalService {
    private final ProfissionalRepository profissionalRepository;

    public ProfissionalService(EntityManager em) {
        this.profissionalRepository = new ProfissionalRepository(em);
    }

    public Profissional buscaPorId(Integer id) {
        return profissionalRepository.buscaPorId(Profissional.class, id);
    }

    public Profissional salvaOuAtualiza(Profissional profissional) {
        return profissionalRepository.salvaOuAtualiza(profissional);
    }

    public void remove(Profissional profissional) {
        profissionalRepository.remove(profissional);
    }

    public List<Profissional> findAll() {
        return profissionalRepository.findAll();
    }
}
