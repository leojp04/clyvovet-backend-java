package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {
}
