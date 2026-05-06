package br.com.fiap.clyvovet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter          // gera todos os getters
@Setter          // gera todos os setters
@NoArgsConstructor   // gera construtor vazio
@AllArgsConstructor  // gera construtor com todos os campos
@Entity
public class EventoClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate data;
    private LocalTime hora;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
    @ManyToOne
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;
    private TipoEvento tipoEvento;
}
