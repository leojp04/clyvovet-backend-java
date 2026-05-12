package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.dto.veterinario.VeterinarioRequest;
import br.com.fiap.clyvovet.dto.veterinario.VeterinarioResponse;
import br.com.fiap.clyvovet.mapper.VeterinarioMapper;
import br.com.fiap.clyvovet.model.Clinica;
import br.com.fiap.clyvovet.model.Veterinario;
import br.com.fiap.clyvovet.repository.ClinicaRepository;
import br.com.fiap.clyvovet.repository.VeterinarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;
    private final VeterinarioMapper veterinarioMapper;
    private final ClinicaRepository clinicaRepository;

    public List<VeterinarioResponse> listarTodos() {
        return veterinarioRepository.findAll().stream().map(veterinarioMapper::veterinarioToResponse).toList();
    }

    public VeterinarioResponse buscarPorId(UUID id) {
        return veterinarioRepository.findById(id).map(veterinarioMapper::veterinarioToResponse).orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
    }

    public VeterinarioResponse salvar(VeterinarioRequest veterinarioRequest) {
        Clinica clinica = clinicaRepository.findById(veterinarioRequest.getClinicaId())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
        Veterinario veterinario = veterinarioMapper.toEntity(veterinarioRequest);
        veterinario.setClinica(clinica);
        return veterinarioMapper.veterinarioToResponse(veterinarioRepository.save(veterinario));
    }

    public VeterinarioResponse atualizar(UUID id, VeterinarioRequest veterinarioRequest) {
        Veterinario veterinario = veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
        Clinica clinica = clinicaRepository.findById(veterinarioRequest.getClinicaId())
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
        veterinarioMapper.atualizar(veterinario, veterinarioRequest, clinica);
        veterinarioRepository.save(veterinario);
        return veterinarioMapper.veterinarioToResponse(veterinario);
    }

    public void deletar(UUID id) {
        veterinarioRepository.deleteById(id);
    }
}