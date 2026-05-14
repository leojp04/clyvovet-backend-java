package br.com.fiap.clyvovet.controller;

import br.com.fiap.clyvovet.dto.tutor.TutorRequest;
import br.com.fiap.clyvovet.dto.tutor.TutorResponse;
import br.com.fiap.clyvovet.service.TutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tutores")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    // Paginação + ordenação + busca por nome e cidade
    @GetMapping
    public ResponseEntity<Page<TutorResponse>> listarTodos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cidade,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(tutorService.listarTodos(nome, cidade, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(tutorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TutorResponse> criar(@Valid @RequestBody TutorRequest tutorRequest) {
        return ResponseEntity.status(201).body(tutorService.salvar(tutorRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorResponse> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody TutorRequest tutorRequest) {
        return ResponseEntity.ok(tutorService.atualizar(id, tutorRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        tutorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}