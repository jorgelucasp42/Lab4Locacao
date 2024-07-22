package com.imobiliaria.repository;

import com.imobiliaria.model.Imovel;
import com.imobiliaria.model.Locacao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Objects;

public class LocacaoRepository extends DAOGenerico<Locacao> {
    public LocacaoRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public Locacao salvaOuAtualiza(Locacao locacao) {
        // Verifique se o imóvel está disponível para locação
        Imovel imovel = locacao.getImovel();
        if (imovel != null) {
            // Verifique se o imóvel está ativo para locação
            if (!isImovelAtivo(imovel)) {
                throw new IllegalArgumentException("Imóvel não está disponível para locação.");
            }

            // Verifique se o imóvel já está alugado
            if (isImovelAlugado(imovel, locacao)) {
                throw new IllegalArgumentException("Imóvel já está alugado.");
            }
        }

        // Salve ou atualize a locação
        if (Objects.isNull(locacao.getId())) {
            this.getManager().persist(locacao);
        } else {
            locacao = this.getManager().merge(locacao);
        }
        return locacao;
    }

    private boolean isImovelAtivo(Imovel imovel) {
        // Verifique se o imóvel está ativo para locação
        return imovel.getValorAluguelSugerido() != null;
    }

    private boolean isImovelAlugado(Imovel imovel, Locacao locacao) {
        // Verifique se o imóvel já está alugado no período de locação
        String jpql = "SELECT COUNT(l) FROM Locacao l WHERE l.imovel = :imovel " +
                "AND l.ativo = TRUE " +
                "AND (l.dataFim IS NULL OR l.dataFim > CURRENT_DATE) " +
                "AND (:startDate <= l.dataFim OR l.dataFim IS NULL)";
        TypedQuery <Long> query = getManager().createQuery(jpql, Long.class);
        query.setParameter("imovel", imovel);
        query.setParameter("startDate", locacao.getDataInicio());

        Long count = query.getSingleResult();
        return count > 0;
    }
}
