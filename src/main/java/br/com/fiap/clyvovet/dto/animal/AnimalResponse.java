package br.com.fiap.clyvovet.dto.animal;



import java.time.LocalDate;
import java.util.UUID;

public record AnimalResponse (UUID id, String nome, String raca, String especie, String porte, String cor, String sexo, LocalDate dataNascimento, String observacao, UUID tutorId, String tutorNome) {
}
