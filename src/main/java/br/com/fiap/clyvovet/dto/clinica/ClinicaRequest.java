package br.com.fiap.clyvovet.dto.clinica;

import br.com.fiap.clyvovet.dto.endereco.EnderecoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ClinicaRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;
    @NotBlank
    @Size(min = 14, max = 14)
    private String cnpj;
    @NotBlank
    @Size(min = 10, max = 11)
    private String telefone;
    @NotBlank
    @Email
    @Size(min = 10, max = 100)
    private String email;
    @Valid
    @NotNull
    private EnderecoRequest endereco;
}
