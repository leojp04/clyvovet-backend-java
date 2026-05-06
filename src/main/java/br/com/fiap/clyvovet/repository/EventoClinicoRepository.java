package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.EventoClinico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EventoClinicoRepository extends JpaRepository<EventoClinico, UUID> {
}
