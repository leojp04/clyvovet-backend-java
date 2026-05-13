package br.com.fiap.clyvovet.dto.eventoClinico;

import br.com.fiap.clyvovet.model.TipoEvento;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EventoClinicoRequest {
    @NotNull
    private LocalDate data;
    @NotNull
    private String hora;
    private String descricao;
    @NotNull
    private UUID veterinarioId;
    @NotNull
    private UUID animalId;
    @NotNull
    private UUID clinicaId;
    @NotNull
    private TipoEvento tipoEvento;
}