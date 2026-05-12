package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.dto.animal.AnimalRequest;
import br.com.fiap.clyvovet.dto.animal.AnimalResponse;
import br.com.fiap.clyvovet.mapper.AnimalMapper;
import br.com.fiap.clyvovet.model.Animal;
import br.com.fiap.clyvovet.model.Tutor;
import br.com.fiap.clyvovet.repository.AnimalRepository;
import br.com.fiap.clyvovet.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final TutorRepository tutorRepository;

    public List<AnimalResponse> listarTodos() {
        return animalRepository.findAll().stream().map(animalMapper::animalToResponse).toList();
    }

    public AnimalResponse buscarPorId(UUID id) {
        return animalRepository.findById(id).map(animalMapper::animalToResponse).orElseThrow(() -> new RuntimeException("Animal não encontrado"));
    }

    public AnimalResponse salvar(AnimalRequest animalRequest) {
        Tutor tutor = tutorRepository.findById(animalRequest.getTutorId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
        Animal animal = animalMapper.toEntity(animalRequest, tutor);
        return animalMapper.animalToResponse(animalRepository.save(animal));
    }

    public AnimalResponse atualizar(UUID id, AnimalRequest animalRequest) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        animal.setNome(animalRequest.getNome());
        animal.setRaca(animalRequest.getRaca());
        animal.setEspecie(animalRequest.getEspecie());
        animal.setPorte(animalRequest.getPorte());
        animal.setCor(animalRequest.getCor());
        animal.setSexo(animalRequest.getSexo());
        animal.setDataNascimento(animalRequest.getDataNascimento());
        animal.setObservacao(animalRequest.getObservacao());
        Tutor tutor = tutorRepository.findById(animalRequest.getTutorId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
        animal.setTutor(tutor);
        animalRepository.save(animal);
        return animalMapper.animalToResponse(animal);
    }

    public void deletar(UUID id) {
        animalRepository.deleteById(id);
    }
}