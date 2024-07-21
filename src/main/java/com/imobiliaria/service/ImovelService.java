package com.imobiliaria.service;

import com.imobiliaria.model.Imovel;
import com.imobiliaria.repository.ImovelRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class ImovelService {
    private final ImovelRepository imovelRepository;

    public ImovelService(EntityManager em) {
        this.imovelRepository = new ImovelRepository(em);
    }

    public Imovel buscaPorId(Integer id) {
        return imovelRepository.buscaPorId(Imovel.class, id);
    }

    public Imovel salvaOuAtualiza(Imovel imovel) {
        return imovelRepository.salvaOuAtualiza(imovel);
    }

    public void remove(Imovel imovel) {
        imovelRepository.remove(imovel);
    }

    public List<Imovel> findAll() {
        return imovelRepository.findAll();
    }
}
