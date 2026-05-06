package br.com.fiap.clyvovet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter          // gera todos os getters
@Setter          // gera todos os setters
@NoArgsConstructor   // gera construtor vazio
@AllArgsConstructor  // gera construtor com todos os campos
@Entity
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    @Embedded
    private Endereco endereco;

}
