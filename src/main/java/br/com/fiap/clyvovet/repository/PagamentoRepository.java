package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.FormaPagamento;
import br.com.fiap.clyvovet.model.Pagamento;
import br.com.fiap.clyvovet.model.StatusPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {


    @Query("SELECT p FROM Pagamento p WHERE " +
            "(:statusPagamento IS NULL OR p.statusPagamento = :statusPagamento) AND " +
            "(:formaPagamento IS NULL OR p.formaPagamento = :formaPagamento)")
    Page<Pagamento> buscarPorFiltros(
            @Param("statusPagamento") StatusPagamento statusPagamento,
            @Param("formaPagamento") FormaPagamento formaPagamento,
            Pageable pageable);
}