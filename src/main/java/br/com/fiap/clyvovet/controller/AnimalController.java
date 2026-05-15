package br.com.fiap.clyvovet.controller;

import br.com.fiap.clyvovet.dto.animal.AnimalRequest;
import br.com.fiap.clyvovet.dto.animal.AnimalResponse;
import br.com.fiap.clyvovet.service.AnimalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/animais")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<Page<AnimalResponse>> listarTodos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String especie,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(animalService.listarTodos(nome, especie, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(animalService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AnimalResponse> criar(@Valid @RequestBody AnimalRequest request) {
        return ResponseEntity.status(201).body(animalService.salvar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody AnimalRequest request) {
        return ResponseEntity.ok(animalService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        animalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}