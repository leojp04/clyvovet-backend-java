package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.model.Animal;
import br.com.fiap.clyvovet.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    public List<Animal> listarTodos() {
        return animalRepository.findAll();
    }

    public Animal buscarPorId(UUID id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
    }

    public Animal salvar(Animal animal) {
        return animalRepository.save(animal);
    }

    public void deletar(UUID id) {
        animalRepository.deleteById(id);
    }
}