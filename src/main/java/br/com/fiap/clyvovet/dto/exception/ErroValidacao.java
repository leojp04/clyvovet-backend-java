package br.com.fiap.clyvovet.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErroValidacao {

    private String campo;
    private String mensagem;
}
