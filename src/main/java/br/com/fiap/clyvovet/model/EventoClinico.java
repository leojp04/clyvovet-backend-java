package br.com.fiap.clyvovet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EventoClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate data;
    private String hora;
    private String descricao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id")
    private Animal animal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;
    @Enumerated(EnumType.STRING)
    private TipoEvento tipoEvento;
}