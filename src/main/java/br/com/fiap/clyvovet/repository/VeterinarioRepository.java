package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.Veterinario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface VeterinarioRepository extends JpaRepository<Veterinario, UUID> {

    @Query("SELECT v FROM Veterinario v WHERE " +
            "(:nome IS NULL OR LOWER(v.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:especialidade IS NULL OR LOWER(v.especialidade) LIKE LOWER(CONCAT('%', :especialidade, '%')))")
    Page<Veterinario> buscarPorFiltros(
            @Param("nome") String nome,
            @Param("especialidade") String especialidade,
            Pageable pageable);
}