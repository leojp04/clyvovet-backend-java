package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.model.Veterinario;
import br.com.fiap.clyvovet.repository.VeterinarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public List<Veterinario> listarTodos() {
        return veterinarioRepository.findAll();
    }

    public Veterinario buscarPorId(UUID id) {
        return veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
    }

    public Veterinario salvar(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    public void deletar(UUID id) {
        veterinarioRepository.deleteById(id);
    }
}