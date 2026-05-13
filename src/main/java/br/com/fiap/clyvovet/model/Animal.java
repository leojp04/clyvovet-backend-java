package br.com.fiap.clyvovet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter          // gera todos os getters
@Setter          // gera todos os setters
@NoArgsConstructor   // gera construtor vazio
@AllArgsConstructor  // gera construtor com todos os campos
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
    private String sexo;
    private LocalDate dataNascimento;
    private String observacao;
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
}
