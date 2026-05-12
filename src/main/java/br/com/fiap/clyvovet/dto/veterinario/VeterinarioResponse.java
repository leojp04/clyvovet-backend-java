package br.com.fiap.clyvovet.dto.veterinario;

import br.com.fiap.clyvovet.dto.endereco.EnderecoResponse;
import br.com.fiap.clyvovet.model.Sexo;

import java.time.LocalDate;
import java.util.UUID;

public record VeterinarioResponse (UUID id, String nome, String cpf, String telefone, String email, String crmv, String especialidade, LocalDate dataNascimento, Sexo sexo, EnderecoResponse endereco, UUID clinicaId, String clinicaNome) {
}
