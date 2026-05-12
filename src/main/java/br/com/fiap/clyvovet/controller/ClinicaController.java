package br.com.fiap.clyvovet.controller;

import br.com.fiap.clyvovet.dto.clinica.ClinicaRequest;
import br.com.fiap.clyvovet.dto.clinica.ClinicaResponse;
import br.com.fiap.clyvovet.service.ClinicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clinicas")
@RequiredArgsConstructor
public class ClinicaController {

    private final ClinicaService clinicaService;

    @GetMapping
    public ResponseEntity<List<ClinicaResponse>> listarTodos() {
        return ResponseEntity.ok(clinicaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaResponse> buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(clinicaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ClinicaResponse> criar(@Valid @RequestBody ClinicaRequest clinicaRequest) {
        return ResponseEntity.status(201).body(clinicaService.salvar(clinicaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicaResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody ClinicaRequest clinicaRequest) {
        return ResponseEntity.ok(clinicaService.atualizar(id, clinicaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        clinicaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}