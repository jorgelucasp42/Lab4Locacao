package com.imobiliaria.service;

import com.imobiliaria.model.Locacao;
import com.imobiliaria.model.Imovel;
import com.imobiliaria.repository.LocacaoRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class LocacaoService {

    private final LocacaoRepository locacaoRepository;

    public LocacaoService(EntityManager manager) {
        this.locacaoRepository = new LocacaoRepository(manager);
    }

    public Locacao salvarLocacao(Locacao locacao) {
        // Verificar disponibilidade do imóvel
        Imovel imovel = locacao.getImovel();
        if (!imovel.isAtivo()) {
            throw new IllegalArgumentException("Imóvel não está disponível para locação");
        }

        return locacaoRepository.salvaOuAtualiza(locacao);
    }

    public Locacao atualizarLocacao(Locacao locacao) {
        return locacaoRepository.salvaOuAtualiza(locacao);
    }

    public List<Locacao> listarLocacoes() {
        return locacaoRepository.buscaTodos();
    }

    public Locacao buscarLocacaoPorId(Integer id) {
        return locacaoRepository.buscaPorId(id);
    }

    public void removerLocacao(Locacao locacao) {
        locacaoRepository.remove(locacao);
    }

    public List<Locacao> listarLocacoesPorCliente(Integer clienteId) {
        return locacaoRepository.buscaPorCliente(clienteId);
    }
}
