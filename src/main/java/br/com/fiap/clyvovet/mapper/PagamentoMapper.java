package br.com.fiap.clyvovet.mapper;

import br.com.fiap.clyvovet.dto.pagamento.PagamentoRequest;
import br.com.fiap.clyvovet.dto.pagamento.PagamentoResponse;
import br.com.fiap.clyvovet.model.EventoClinico;
import br.com.fiap.clyvovet.model.Pagamento;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PagamentoMapper {

    public Pagamento toEntity(PagamentoRequest request, EventoClinico eventoClinico) {
        Pagamento pagamento = new Pagamento();
        pagamento.setFormaPagamento(request.getFormaPagamento());
        pagamento.setValor(request.getValor());
        pagamento.setDataPagamento(request.getDataPagamento());
        pagamento.setDescricao(request.getDescricao());
        pagamento.setObservacao(request.getObservacao());
        pagamento.setStatusPagamento(request.getStatusPagamento());
        pagamento.setEventoClinico(eventoClinico);
        return pagamento;
    }

    public PagamentoResponse toResponse(Pagamento pagamento) {
        UUID eventoClinicoId = pagamento.getEventoClinico() != null
                ? pagamento.getEventoClinico().getId()
                : null;

        PagamentoResponse response = new PagamentoResponse();
        response.setId(pagamento.getId());
        response.setFormaPagamento(pagamento.getFormaPagamento());
        response.setValor(pagamento.getValor());
        response.setDataPagamento(pagamento.getDataPagamento());
        response.setDescricao(pagamento.getDescricao());
        response.setObservacao(pagamento.getObservacao());
        response.setStatusPagamento(pagamento.getStatusPagamento());
        response.setEventoClinicoId(eventoClinicoId);
        return response;
    }
}