package com.imobiliaria.service;

import com.imobiliaria.model.Locacao;
import com.imobiliaria.repository.LocacaoRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class LocacaoService {
    private final LocacaoRepository locacaoRepository;

    public LocacaoService(EntityManager em) {
        this.locacaoRepository = new LocacaoRepository(em);
    }

    public Locacao buscaPorId(Integer id) {
        return locacaoRepository.buscaPorId(Locacao.class, id);
    }

    public Locacao salvaOuAtualiza(Locacao locacao) {
        return locacaoRepository.salvaOuAtualiza(locacao);
    }

    public void remove(Locacao locacao) {
        locacaoRepository.remove(locacao);
    }

    public List<Locacao> findAll() {
        return locacaoRepository.findAll();
    }
}
