package br.com.fiap.clyvovet.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class EventoClinico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "data_evento")
    private LocalDate data;
    @Column(name = "hora_evento")
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
    @Column(name = "tipo_evento")
    private TipoEvento tipoEvento;
}