package com.imobiliaria.service;

import com.imobiliaria.model.Aluguel;
import com.imobiliaria.repository.AluguelRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class AluguelService {
    private final AluguelRepository aluguelRepository;

    public AluguelService(EntityManager em) {
        this.aluguelRepository = new AluguelRepository(em);
    }

    public Aluguel buscaPorId(Integer id) {
        return aluguelRepository.buscaPorId(Aluguel.class, id);
    }

    public Aluguel salvaOuAtualiza(Aluguel aluguel) {
        return aluguelRepository.salvaOuAtualiza(aluguel);
    }

    public void remove(Aluguel aluguel) {
        aluguelRepository.remove(aluguel);
    }

    public List<Aluguel> findAll() {
        return aluguelRepository.findAll();
    }
}
