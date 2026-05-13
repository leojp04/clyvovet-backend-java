package br.com.fiap.clyvovet.mapper;

import br.com.fiap.clyvovet.dto.eventoClinico.EventoClinicoRequest;
import br.com.fiap.clyvovet.dto.eventoClinico.EventoClinicoResponse;
import br.com.fiap.clyvovet.model.Animal;
import br.com.fiap.clyvovet.model.Clinica;
import br.com.fiap.clyvovet.model.EventoClinico;
import br.com.fiap.clyvovet.model.Veterinario;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventoClinicoMapper {

    public EventoClinicoResponse toResponse(EventoClinico evento) {
        UUID veterinarioId = evento.getVeterinario() != null ? evento.getVeterinario().getId() : null;
        String veterinarioNome = evento.getVeterinario() != null ? evento.getVeterinario().getNome() : null;
        UUID animalId = evento.getAnimal() != null ? evento.getAnimal().getId() : null;
        String animalNome = evento.getAnimal() != null ? evento.getAnimal().getNome() : null;
        UUID clinicaId = evento.getClinica() != null ? evento.getClinica().getId() : null;
        String clinicaNome = evento.getClinica() != null ? evento.getClinica().getNome() : null;

        return new EventoClinicoResponse(
                evento.getId(),
                evento.getData(),
                evento.getHora(),
                evento.getDescricao(),
                evento.getTipoEvento(),
                veterinarioId,
                veterinarioNome,
                animalId,
                animalNome,
                clinicaId,
                clinicaNome
        );
    }

    public EventoClinico toEntity(EventoClinicoRequest request, Veterinario veterinario, Animal animal, Clinica clinica) {
        EventoClinico evento = new EventoClinico();
        evento.setData(request.getData());
        evento.setHora(request.getHora());
        evento.setDescricao(request.getDescricao());
        evento.setTipoEvento(request.getTipoEvento());
        evento.setVeterinario(veterinario);
        evento.setAnimal(animal);
        evento.setClinica(clinica);
        return evento;
    }

    public void atualizar(EventoClinico evento, EventoClinicoRequest request, Veterinario veterinario, Animal animal, Clinica clinica) {
        evento.setData(request.getData());
        evento.setHora(request.getHora());
        evento.setDescricao(request.getDescricao());
        evento.setTipoEvento(request.getTipoEvento());
        evento.setVeterinario(veterinario);
        evento.setAnimal(animal);
        evento.setClinica(clinica);
    }
}