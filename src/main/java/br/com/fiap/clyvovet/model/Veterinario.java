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
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String email;
    private String telefone;
    @Embedded
    private Endereco endereco;
    private String especialidade;
    private String crmv;
    @ManyToOne
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;

}
