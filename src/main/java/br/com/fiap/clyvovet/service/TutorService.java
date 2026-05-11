package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.dto.tutor.TutorRequest;
import br.com.fiap.clyvovet.dto.tutor.TutorResponse;
import br.com.fiap.clyvovet.mapper.EnderecoMapper;
import br.com.fiap.clyvovet.mapper.TutorMapper;
import br.com.fiap.clyvovet.model.Tutor;
import br.com.fiap.clyvovet.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;
    private final TutorMapper tutorMapper;
    private final EnderecoMapper enderecoMapper;

    public List<TutorResponse> listarTodos() {
        return tutorRepository.findAll().stream().map(tutorMapper::tutorToResponse).toList();
    }

    public TutorResponse buscarPorId(UUID id) {
        return tutorRepository.findById(id).map(tutorMapper::tutorToResponse).orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
    }

    public TutorResponse salvar(TutorRequest tutorRequest) {
        Tutor tutor = tutorMapper.requestToTutor(tutorRequest);
        return tutorMapper.tutorToResponse(tutorRepository.save(tutor));
    }

    public TutorResponse atualizar(UUID id, TutorRequest tutorRequest) {
        Tutor tutor = tutorRepository.findById(id).orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
        tutor.setNome(tutorRequest.getNome());
        tutor.setCpf(tutorRequest.getCpf());
        tutor.setEmail(tutorRequest.getEmail());
        tutor.setTelefone(tutorRequest.getTelefone());
        tutor.setSexo(tutorRequest.getSexo());
        tutor.setDataNascimento(tutorRequest.getDataNascimento());
        tutor.setEndereco(enderecoMapper.requestToEndereco(tutorRequest.getEndereco()));
        tutorRepository.save(tutor);
        return tutorMapper.tutorToResponse(tutor);
    }

    public void deletar(UUID id) {
        tutorRepository.deleteById(id);
    }
}
