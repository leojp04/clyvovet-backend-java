package br.com.fiap.clyvovet.mapper;

import br.com.fiap.clyvovet.dto.eventoClinico.EventoClinicoRequest;
import br.com.fiap.clyvovet.dto.eventoClinico.EventoClinicoResponse;
import br.com.fiap.clyvovet.model.Animal;
import br.com.fiap.clyvovet.model.Clinica;
import br.com.fiap.clyvovet.model.EventoClinico;
import br.com.fiap.clyvovet.model.Veterinario;
import org.springframework.stereotype.Component;

@Component
public class EventoClinicoMapper {

    public EventoClinicoResponse toResponse(EventoClinico evento) {
        return new EventoClinicoResponse(
                evento.getId(),
                evento.getData(),
                evento.getHora(),
                evento.getDescricao(),
                evento.getTipoEvento(),
                evento.getVeterinario().getId(),
                evento.getVeterinario().getNome(),
                evento.getAnimal().getId(),
                evento.getAnimal().getNome(),
                evento.getClinica().getId(),
                evento.getClinica().getNome()
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