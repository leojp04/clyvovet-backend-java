package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TutorRepository  extends JpaRepository <Tutor, UUID>{
}
