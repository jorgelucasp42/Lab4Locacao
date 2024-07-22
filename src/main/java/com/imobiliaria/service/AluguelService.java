package com.imobiliaria.service;

import com.imobiliaria.model.Aluguel;
import com.imobiliaria.repository.AluguelRepository;

import javax.persistence.EntityManager;
import java.util.Date;

public class AluguelService {
    private final AluguelRepository aluguelRepository;

    public AluguelService(EntityManager em) {
        this.aluguelRepository = new AluguelRepository(em);
    }

    /**
     * Registra o pagamento de um aluguel.
     *
     * @param aluguelId     ID do aluguel.
     * @param dataPagamento Data do pagamento.
     * @return O aluguel atualizado com o valor pago e a data de pagamento.
     * @throws IllegalArgumentException Se o aluguel não for encontrado.
     */
    public Aluguel registrarPagamento(Integer aluguelId, Date dataPagamento) {
        Aluguel aluguel = aluguelRepository.buscaPorId(Aluguel.class, aluguelId);

        if (aluguel == null) {
            throw new IllegalArgumentException("Aluguel não encontrado.");
        }

        // Calcule o valor a ser pago, incluindo multas
        Double valorPago = calcularValorPago(aluguel.getDataVencimento(), dataPagamento, aluguel.getValorPago());

        // Atualize o aluguel com o valor pago e a data de pagamento
        aluguel.setDataPagamento(dataPagamento);
        aluguel.setValorPago(valorPago);

        // Salve ou atualize o aluguel
        aluguelRepository.salvaOuAtualiza(aluguel);

        return aluguel;
    }

    /**
     * Calcula o valor a ser pago, incluindo multas se o pagamento for atrasado.
     *
     * @param dataVencimento Data de vencimento do aluguel.
     * @param dataPagamento  Data do pagamento.
     * @param valorAluguel   Valor do aluguel.
     * @return Valor a ser pago, incluindo multas se houver atraso.
     */
    private Double calcularValorPago(Date dataVencimento, Date dataPagamento, Double valorAluguel) {
        if (dataPagamento.before(dataVencimento) || dataPagamento.equals(dataVencimento)) {
            // Pagamento dentro do prazo, sem multa
            return valorAluguel;
        }

        // Calcule o número de dias de atraso
        long diasAtraso = (dataPagamento.getTime() - dataVencimento.getTime()) / (1000 * 60 * 60 * 24);
        if (diasAtraso <= 0) {
            return valorAluguel;
        }

        // Calcule a multa
        double taxaMultaDiaria = 0.0033; // 0,33% ao dia
        double multaMaxima = 0.20; // 20% do valor do aluguel
        double multa = Math.min(diasAtraso * taxaMultaDiaria * valorAluguel, multaMaxima * valorAluguel);

        // Calcule o valor total a ser pago
        return valorAluguel + multa;
    }
}
