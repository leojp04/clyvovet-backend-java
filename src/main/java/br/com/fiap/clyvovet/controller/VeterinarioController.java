package br.com.fiap.clyvovet.controller;

import br.com.fiap.clyvovet.dto.veterinario.VeterinarioRequest;
import br.com.fiap.clyvovet.dto.veterinario.VeterinarioResponse;
import br.com.fiap.clyvovet.service.VeterinarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/veterinarios")
@RequiredArgsConstructor
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @GetMapping
    public ResponseEntity<List<VeterinarioResponse>> listarTodos() {
        return ResponseEntity.ok(veterinarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(veterinarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<VeterinarioResponse> criar(@Valid @RequestBody VeterinarioRequest veterinarioRequest) {
        return ResponseEntity.status(201).body(veterinarioService.salvar(veterinarioRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody VeterinarioRequest veterinarioRequest) {
        return ResponseEntity.ok(veterinarioService.atualizar(id, veterinarioRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        veterinarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
