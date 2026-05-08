package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.model.EventoClinico;
import br.com.fiap.clyvovet.repository.EventoClinicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventoClinicoService {

    private final EventoClinicoRepository eventoClinicoRepository;

    public List<EventoClinico> listarTodos() {
        return eventoClinicoRepository.findAll();
    }

    public EventoClinico buscarPorId(UUID id) {
        return eventoClinicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento clínico não encontrado"));
    }

    public EventoClinico salvar(EventoClinico eventoClinico) {
        return eventoClinicoRepository.save(eventoClinico);
    }

    public void deletar(UUID id) {
        eventoClinicoRepository.deleteById(id);
    }
}