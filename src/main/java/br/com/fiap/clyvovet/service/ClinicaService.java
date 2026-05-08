package br.com.fiap.clyvovet.service;

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

    public List<Clinica> listarTodos() {
        return clinicaRepository.findAll();
    }

    public Clinica buscarPorId(UUID id) {
        return clinicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
    }

    public Clinica salvar(Clinica clinica) {
        return clinicaRepository.save(clinica);
    }

    public void deletar(UUID id) {
        clinicaRepository.deleteById(id);
    }
}