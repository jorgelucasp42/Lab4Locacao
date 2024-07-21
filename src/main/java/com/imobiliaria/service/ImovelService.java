package com.imobiliaria.service;

import com.imobiliaria.model.Imovel;
import com.imobiliaria.repository.ImovelRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class ImovelService {
    private ImovelRepository imovelRepository;

    public ImovelService(EntityManager em) {
        this.imovelRepository = new ImovelRepository(em);
    }

    public List<Imovel> findAll() {
        return imovelRepository.findAll();
    }

    public Imovel findById(Integer id) {
        return imovelRepository.findById(id);
    }

    public void save(Imovel imovel) {
        imovelRepository.save(imovel);
    }

    public void deleteById(Integer id) {
        imovelRepository.deleteById(id);
    }
}
