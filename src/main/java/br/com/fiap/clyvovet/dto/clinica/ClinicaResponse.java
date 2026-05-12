package br.com.fiap.clyvovet.dto.clinica;

import java.util.UUID;

public record ClinicaResponse (UUID id, String nome, String cnpj, String telefone, String email, br.com.fiap.clyvovet.dto.endereco.EnderecoResponse endereco) {
}
