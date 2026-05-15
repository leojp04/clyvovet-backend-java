package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.dto.animal.AnimalRequest;
import br.com.fiap.clyvovet.dto.animal.AnimalResponse;
import br.com.fiap.clyvovet.mapper.AnimalMapper;
import br.com.fiap.clyvovet.model.Animal;
import br.com.fiap.clyvovet.model.Tutor;
import br.com.fiap.clyvovet.repository.AnimalRepository;
import br.com.fiap.clyvovet.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final TutorRepository tutorRepository;

    @Cacheable(value = "animais", key = "#nome + '-' + #especie + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<AnimalResponse> listarTodos(String nome, String especie, Pageable pageable) {
        return animalRepository.buscarPorFiltros(nome, especie, pageable)
                .map(animalMapper::animalToResponse);
    }

    public AnimalResponse buscarPorId(UUID id) {
        return animalRepository.findById(id)
                .map(animalMapper::animalToResponse)
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado com ID: " + id));
    }

    @CacheEvict(value = "animais", allEntries = true)
    public AnimalResponse salvar(AnimalRequest request) {
        Tutor tutor = tutorRepository.findById(request.getTutorId())
                .orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado com ID: " + request.getTutorId()));
        Animal animal = animalMapper.toEntity(request, tutor);
        return animalMapper.animalToResponse(animalRepository.save(animal));
    }

    @CacheEvict(value = "animais", allEntries = true)
    public AnimalResponse atualizar(UUID id, AnimalRequest request) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado com ID: " + id));
        Tutor tutor = tutorRepository.findById(request.getTutorId())
                .orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado com ID: " + request.getTutorId()));
        animal.setNome(request.getNome());
        animal.setRaca(request.getRaca());
        animal.setEspecie(request.getEspecie());
        animal.setPorte(request.getPorte());
        animal.setCor(request.getCor());
        animal.setSexo(request.getSexo());
        animal.setDataNascimento(request.getDataNascimento());
        animal.setObservacao(request.getObservacao());
        animal.setTutor(tutor);
        return animalMapper.animalToResponse(animalRepository.save(animal));
    }

    @CacheEvict(value = "animais", allEntries = true)
    public void deletar(UUID id) {
        if (!animalRepository.existsById(id)) {
            throw new EntityNotFoundException("Animal não encontrado com ID: " + id);
        }
        animalRepository.deleteById(id);
    }
}