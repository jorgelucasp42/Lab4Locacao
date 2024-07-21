package com.imobiliaria.service;

import com.imobiliaria.model.ServicoImovel;
import com.imobiliaria.repository.ServicoImovelRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class ServicoImovelService {
    private final ServicoImovelRepository servicoImovelRepository;

    public ServicoImovelService(EntityManager em) {
        this.servicoImovelRepository = new ServicoImovelRepository(em);
    }

    public ServicoImovel buscaPorId(Integer id) {
        return servicoImovelRepository.buscaPorId(ServicoImovel.class, id);
    }

    public ServicoImovel salvaOuAtualiza(ServicoImovel servicoImovel) {
        return servicoImovelRepository.salvaOuAtualiza(servicoImovel);
    }

    public void remove(ServicoImovel servicoImovel) {
        servicoImovelRepository.remove(servicoImovel);
    }

    public List<ServicoImovel> findAll() {
        return servicoImovelRepository.findAll();
    }
}
