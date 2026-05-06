package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VeterinarioRepository extends JpaRepository<Veterinario, UUID> {
}
