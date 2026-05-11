package br.com.fiap.clyvovet.mapper;

import br.com.fiap.clyvovet.dto.tutor.TutorRequest;
import br.com.fiap.clyvovet.dto.tutor.TutorResponse;
import br.com.fiap.clyvovet.model.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TutorMapper {
    private final EnderecoMapper enderecoMapper;
    public TutorResponse tutorToResponse(Tutor tutor) {
    return new TutorResponse(tutor.getId(), tutor.getNome(), tutor.getEmail(), tutor.getTelefone(), tutor.getSexo().toString(), tutor.getDataNascimento(), tutor.getCpf(), enderecoMapper.enderecoToResponse(tutor.getEndereco()));
    }



    public Tutor requestToTutor(TutorRequest request){
        Tutor tutor = new Tutor();
        tutor.setCpf(request.getCpf());
        tutor.setNome(request.getNome());
        tutor.setDataNascimento(request.getDataNascimento());
        tutor.setSexo(request.getSexo());
        tutor.setEmail(request.getEmail());
        tutor.setTelefone(request.getTelefone());
        tutor.setEndereco(enderecoMapper.requestToEndereco(request.getEndereco()));
        return tutor;
    }

}
