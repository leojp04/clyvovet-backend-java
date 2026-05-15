package br.com.fiap.clyvovet.controller;

import br.com.fiap.clyvovet.dto.veterinario.VeterinarioRequest;
import br.com.fiap.clyvovet.dto.veterinario.VeterinarioResponse;
import br.com.fiap.clyvovet.service.VeterinarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/veterinarios")
@RequiredArgsConstructor
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @GetMapping
    public ResponseEntity<Page<VeterinarioResponse>> listarTodos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String especialidade,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(veterinarioService.listarTodos(nome, especialidade, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(veterinarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<VeterinarioResponse> criar(@Valid @RequestBody VeterinarioRequest request) {
        return ResponseEntity.status(201).body(veterinarioService.salvar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioResponse> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody VeterinarioRequest request) {
        return ResponseEntity.ok(veterinarioService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        veterinarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}