package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {

    @Query("SELECT a FROM Animal a WHERE " +
            "(:nome IS NULL OR LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:especie IS NULL OR LOWER(a.especie) LIKE LOWER(CONCAT('%', :especie, '%')))")
    Page<Animal> buscarPorFiltros(
            @Param("nome") String nome,
            @Param("especie") String especie,
            Pageable pageable);
}