package br.com.fiap.clyvovet.dto.tutor;

import br.com.fiap.clyvovet.dto.endereco.EnderecoResponse;

import java.time.LocalDate;
import java.util.UUID;

public record TutorResponse(UUID id, String nome, String email, String telefone, String sexo, LocalDate dataNascimento, String cpf, EnderecoResponse endereco) {

}
