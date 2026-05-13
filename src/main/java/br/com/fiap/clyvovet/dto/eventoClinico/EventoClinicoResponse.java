package br.com.fiap.clyvovet.dto.eventoClinico;

import br.com.fiap.clyvovet.model.TipoEvento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record EventoClinicoResponse(
        UUID id,
        LocalDate data,
        LocalTime hora,
        String descricao,
        TipoEvento tipoEvento,
        UUID veterinarioId,
        String veterinarioNome,
        UUID animalId,
        String animalNome,
        UUID clinicaId,
        String clinicaNome
) {}