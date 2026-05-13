package br.com.fiap.clyvovet.dto.pagamento;

import br.com.fiap.clyvovet.model.FormaPagamento;
import br.com.fiap.clyvovet.model.StatusPagamento;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class PagamentoResponse {

    private UUID id;
    private FormaPagamento formaPagamento;
    private BigDecimal valor;
    private LocalDate dataPagamento;
    private String descricao;
    private String observacao;
    private UUID eventoClinicoId;
    private StatusPagamento statusPagamento;
}