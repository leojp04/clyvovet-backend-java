package br.com.fiap.clyvovet.dto.pagamento;

import br.com.fiap.clyvovet.model.FormaPagamento;
import br.com.fiap.clyvovet.model.StatusPagamento;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class PagamentoRequest {

    @NotNull(message = "Forma de pagamento é obrigatória")
    private FormaPagamento formaPagamento;

    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    @Digits(integer = 9, fraction = 2, message = "Valor inválido: máximo 9 dígitos inteiros e 2 decimais")
    private BigDecimal valor;

    @NotNull(message = "Data de pagamento é obrigatória")
    @PastOrPresent(message = "Data de pagamento não pode ser futura")
    private LocalDate dataPagamento;

    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
    private String descricao;

    @Size(max = 500, message = "Observação deve ter no máximo 500 caracteres")
    private String observacao;

    @NotNull(message = "ID do evento clínico é obrigatório")
    private UUID eventoClinicoId;

    @NotNull(message = "Status de pagamento é obrigatório")
    private StatusPagamento statusPagamento;
}