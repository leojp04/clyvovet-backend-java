package br.com.fiap.clyvovet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@Getter          // gera todos os getters
@Setter          // gera todos os setters
@NoArgsConstructor   // gera construtor vazio
@AllArgsConstructor  // gera construtor com todos os campos

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
    private BigDecimal valor;
    private LocalDate dataPagamento;
    private String descricao;
    private String observacao;
    @ManyToOne
    @JoinColumn(name = "evento_clinico_id")
    private EventoClinico eventoClinico;
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;
}
