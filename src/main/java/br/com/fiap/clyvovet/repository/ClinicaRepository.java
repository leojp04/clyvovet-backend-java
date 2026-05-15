package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.Clinica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ClinicaRepository extends JpaRepository<Clinica, UUID> {

    @Query("SELECT c FROM Clinica c WHERE " +
            "(:nome IS NULL OR LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:cidade IS NULL OR LOWER(c.endereco.cidade) LIKE LOWER(CONCAT('%', :cidade, '%')))")
    Page<Clinica> buscarPorFiltros(
            @Param("nome") String nome,
            @Param("cidade") String cidade,
            Pageable pageable);
}