package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClinicaRepository extends JpaRepository<Clinica, UUID> {
}
