package br.com.fiap.clyvovet.mapper;

import br.com.fiap.clyvovet.dto.clinica.ClinicaRequest;
import br.com.fiap.clyvovet.dto.clinica.ClinicaResponse;
import br.com.fiap.clyvovet.model.Clinica;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClinicaMapper {
    private final EnderecoMapper enderecoMapper;

    public ClinicaResponse clinicaToResponse(Clinica clinica) {
        return new ClinicaResponse(clinica.getId(),clinica.getNome(), clinica.getCnpj(), clinica.getTelefone(), clinica.getEmail(),enderecoMapper.enderecoToResponse(clinica.getEndereco()));
    }

    public Clinica toEntity(ClinicaRequest request){
        Clinica clinica = new Clinica();
        clinica.setNome(request.getNome());
        clinica.setCnpj(request.getCnpj());
        clinica.setTelefone(request.getTelefone());
        clinica.setEmail(request.getEmail());
        clinica.setEndereco(enderecoMapper.requestToEndereco(request.getEndereco()));
        return clinica;
    }

    public void atualizar(Clinica clinica, ClinicaRequest request) {
        clinica.setNome(request.getNome());
        clinica.setCnpj(request.getCnpj());
        clinica.setTelefone(request.getTelefone());
        clinica.setEmail(request.getEmail());
        clinica.setEndereco(enderecoMapper.requestToEndereco(request.getEndereco()));
    }
}
