package com.imobiliaria.service;

import com.imobiliaria.model.TipoImovel;
import com.imobiliaria.repository.TipoImovelRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class TipoImovelService {
    private final TipoImovelRepository tipoImovelRepository;

    public TipoImovelService(EntityManager em) {
        this.tipoImovelRepository = new TipoImovelRepository(em);
    }

    public TipoImovel buscaPorId(Integer id) {
        return tipoImovelRepository.buscaPorId(TipoImovel.class, id);
    }

    public TipoImovel salvaOuAtualiza(TipoImovel tipoImovel) {
        return tipoImovelRepository.salvaOuAtualiza(tipoImovel);
    }

    public void remove(TipoImovel tipoImovel) {
        tipoImovelRepository.remove(tipoImovel);
    }

    public List<TipoImovel> findAll() {
        return tipoImovelRepository.findAll();
    }
}
