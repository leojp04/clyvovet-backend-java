package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.model.Tutor;
import br.com.fiap.clyvovet.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;

    public List<Tutor> listarTodos() {
        return tutorRepository.findAll();
    }

    public Tutor buscarPorId(UUID id) {
        return tutorRepository.findById(id).orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
    }

    public Tutor salvar(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public void deletar(UUID id) {
        tutorRepository.deleteById(id);
    }
}
