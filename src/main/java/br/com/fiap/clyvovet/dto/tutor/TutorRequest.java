package br.com.fiap.clyvovet.dto.tutor;

import br.com.fiap.clyvovet.dto.endereco.EnderecoRequest;
import br.com.fiap.clyvovet.model.Sexo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TutorRequest {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Sexo sexo;
    private LocalDate dataNascimento;
    private EnderecoRequest endereco;
}
