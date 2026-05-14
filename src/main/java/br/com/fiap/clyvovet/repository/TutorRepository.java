package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.Tutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TutorRepository extends JpaRepository<Tutor, UUID> {

    // Busca por nome (parcial) e/ou cidade — parâmetros opcionais
    @Query("SELECT t FROM Tutor t WHERE " +
            "(:nome IS NULL OR LOWER(t.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:cidade IS NULL OR LOWER(t.endereco.cidade) LIKE LOWER(CONCAT('%', :cidade, '%')))")
    Page<Tutor> buscarPorFiltros(
            @Param("nome") String nome,
            @Param("cidade") String cidade,
            Pageable pageable);
}