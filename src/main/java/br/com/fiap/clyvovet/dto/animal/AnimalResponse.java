package br.com.fiap.clyvovet.dto.animal;

import br.com.fiap.clyvovet.model.Sexo;

import java.time.LocalDate;
import java.util.UUID;

public record AnimalResponse (UUID id, String nome, String raca, String especie, String porte, String cor, Sexo sexo, LocalDate dataNascimento, String observacao, UUID tutorId, String tutorNome) {
}
