package br.com.fiap.clyvovet.mapper;

import br.com.fiap.clyvovet.dto.endereco.EnderecoRequest;
import br.com.fiap.clyvovet.dto.endereco.EnderecoResponse;
import br.com.fiap.clyvovet.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public Endereco requestToEndereco(EnderecoRequest enderecoRequest){
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setNumero(enderecoRequest.getNumero());
        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setCep(enderecoRequest.getCep());
        endereco.setComplemento(enderecoRequest.getComplemento());
        return endereco;
    }

    public EnderecoResponse enderecoToResponse(Endereco endereco) {
        return new EnderecoResponse(
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }
}
