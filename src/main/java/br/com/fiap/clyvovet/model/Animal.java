package br.com.fiap.clyvovet.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String raca;
    private String especie;
    private String porte;
    private String cor;
    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private SexoAnimal sexo;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @Column(name = "observacoes")
    private String observacao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
}