package br.com.fiap.clyvovet.dto.clinica;

import br.com.fiap.clyvovet.dto.endereco.EnderecoResponse;

import java.util.UUID;

public record ClinicaResponse (UUID id, String nome, String cnpj, String telefone, String email, EnderecoResponse endereco) {
}
