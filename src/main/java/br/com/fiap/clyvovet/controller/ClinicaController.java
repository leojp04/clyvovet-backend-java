package br.com.fiap.clyvovet.controller;

import br.com.fiap.clyvovet.dto.clinica.ClinicaRequest;
import br.com.fiap.clyvovet.dto.clinica.ClinicaResponse;
import br.com.fiap.clyvovet.service.ClinicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clinicas")
@RequiredArgsConstructor
public class ClinicaController {

    private final ClinicaService clinicaService;

    @GetMapping
    public ResponseEntity<Page<ClinicaResponse>> listarTodos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cidade,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(clinicaService.listarTodos(nome, cidade, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(clinicaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ClinicaResponse> criar(@Valid @RequestBody ClinicaRequest request) {
        return ResponseEntity.status(201).body(clinicaService.salvar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicaResponse> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody ClinicaRequest request) {
        return ResponseEntity.ok(clinicaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        clinicaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}