package br.com.fiap.clyvovet.controller;

import br.com.fiap.clyvovet.dto.eventoClinico.EventoClinicoRequest;
import br.com.fiap.clyvovet.dto.eventoClinico.EventoClinicoResponse;
import br.com.fiap.clyvovet.model.TipoEvento;
import br.com.fiap.clyvovet.service.EventoClinicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/eventos-clinicos")
@RequiredArgsConstructor
public class EventoClinicoController {

    private final EventoClinicoService eventoClinicoService;

    @GetMapping
    public ResponseEntity<Page<EventoClinicoResponse>> listarTodos(
            @RequestParam(required = false) TipoEvento tipoEvento,
            @RequestParam(required = false) String animalNome,
            @PageableDefault(size = 10, sort = "data") Pageable pageable) {
        return ResponseEntity.ok(eventoClinicoService.listarTodos(tipoEvento, animalNome, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoClinicoResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(eventoClinicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EventoClinicoResponse> criar(@Valid @RequestBody EventoClinicoRequest request) {
        return ResponseEntity.status(201).body(eventoClinicoService.salvar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoClinicoResponse> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody EventoClinicoRequest request) {
        return ResponseEntity.ok(eventoClinicoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        eventoClinicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}