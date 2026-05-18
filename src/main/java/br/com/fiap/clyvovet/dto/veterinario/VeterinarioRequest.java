package br.com.fiap.clyvovet.dto.veterinario;

import br.com.fiap.clyvovet.dto.endereco.EnderecoRequest;
import br.com.fiap.clyvovet.model.Sexo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VeterinarioRequest {

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;
    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    private Sexo sexo;
    @NotBlank
    @Email
    @Size(min = 10, max = 100)
    private String email;
    @NotBlank
    @Size(min = 10, max = 11)
    private String telefone;
    @Valid
    @NotNull
    private EnderecoRequest endereco;
    @NotBlank
    @Size(min = 3, max = 100)
    private String especialidade;
    @NotBlank
    @Size(min = 4, max = 6)
    private String crmv;
    @NotNull
    private UUID clinicaId;

}
