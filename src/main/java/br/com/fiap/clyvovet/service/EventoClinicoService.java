package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.dto.eventoClinico.EventoClinicoRequest;
import br.com.fiap.clyvovet.dto.eventoClinico.EventoClinicoResponse;
import br.com.fiap.clyvovet.mapper.EventoClinicoMapper;
import br.com.fiap.clyvovet.model.Animal;
import br.com.fiap.clyvovet.model.Clinica;
import br.com.fiap.clyvovet.model.EventoClinico;
import br.com.fiap.clyvovet.model.Veterinario;
import br.com.fiap.clyvovet.repository.AnimalRepository;
import br.com.fiap.clyvovet.repository.ClinicaRepository;
import br.com.fiap.clyvovet.repository.EventoClinicoRepository;
import br.com.fiap.clyvovet.repository.VeterinarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventoClinicoService {

    private final EventoClinicoRepository eventoClinicoRepository;
    private final EventoClinicoMapper eventoClinicoMapper;
    private final VeterinarioRepository veterinarioRepository;
    private final AnimalRepository animalRepository;
    private final ClinicaRepository clinicaRepository;

    public List<EventoClinicoResponse> listarTodos() {
        return eventoClinicoRepository.findAll().stream().map(eventoClinicoMapper::toResponse).toList();
    }

    public EventoClinicoResponse buscarPorId(UUID id) {
        return eventoClinicoRepository.findById(id).map(eventoClinicoMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Evento clínico não encontrado"));
    }

    public EventoClinicoResponse salvar(EventoClinicoRequest request) {
        Veterinario veterinario = veterinarioRepository.findById(request.getVeterinarioId())
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        Clinica clinica = clinicaRepository.findById(request.getClinicaId())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
        EventoClinico evento = eventoClinicoMapper.toEntity(request, veterinario, animal, clinica);
        return eventoClinicoMapper.toResponse(eventoClinicoRepository.save(evento));
    }

    public EventoClinicoResponse atualizar(UUID id, EventoClinicoRequest request) {
        EventoClinico evento = eventoClinicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento clínico não encontrado"));
        Veterinario veterinario = veterinarioRepository.findById(request.getVeterinarioId())
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        Clinica clinica = clinicaRepository.findById(request.getClinicaId())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
        eventoClinicoMapper.atualizar(evento, request, veterinario, animal, clinica);
        eventoClinicoRepository.save(evento);
        return eventoClinicoMapper.toResponse(evento);
    }

    public void deletar(UUID id) {
        eventoClinicoRepository.deleteById(id);
    }
}