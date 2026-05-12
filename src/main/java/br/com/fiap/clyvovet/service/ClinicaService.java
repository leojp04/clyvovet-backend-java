package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.dto.clinica.ClinicaRequest;
import br.com.fiap.clyvovet.dto.clinica.ClinicaResponse;
import br.com.fiap.clyvovet.mapper.ClinicaMapper;
import br.com.fiap.clyvovet.model.Clinica;
import br.com.fiap.clyvovet.repository.ClinicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClinicaService {

    private final ClinicaRepository clinicaRepository;
    private final ClinicaMapper clinicaMapper;

    public List<ClinicaResponse> listarTodos() {
        return clinicaRepository.findAll().stream().map(clinicaMapper::clinicaToResponse).toList();
    }

    public ClinicaResponse buscarPorId(UUID id) {
        return clinicaRepository.findById(id).map(clinicaMapper::clinicaToResponse).orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
    }

    public ClinicaResponse salvar(ClinicaRequest clinicaRequest) {
        Clinica clinica = clinicaMapper.toEntity(clinicaRequest);
        return clinicaMapper.clinicaToResponse(clinicaRepository.save(clinica));
    }

    public ClinicaResponse atualizar(UUID id, ClinicaRequest clinicaRequest) {
        Clinica clinica = clinicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
        clinicaMapper.atualizar(clinica, clinicaRequest);
        clinicaRepository.save(clinica);
        return clinicaMapper.clinicaToResponse(clinica);
    }

    public void deletar(UUID id) {
        clinicaRepository.deleteById(id);
    }
}