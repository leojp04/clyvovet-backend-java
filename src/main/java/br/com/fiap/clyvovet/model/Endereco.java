package br.com.fiap.clyvovet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Embeddable
public class Endereco {
    @Column(name = "rua")
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;
}