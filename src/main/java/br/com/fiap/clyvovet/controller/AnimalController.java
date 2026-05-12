package br.com.fiap.clyvovet.controller;

import br.com.fiap.clyvovet.dto.animal.AnimalRequest;
import br.com.fiap.clyvovet.dto.animal.AnimalResponse;
import br.com.fiap.clyvovet.service.AnimalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animais")
@RequiredArgsConstructor

public class AnimalController {
    private final AnimalService animalService;


    @GetMapping
    public ResponseEntity<List<AnimalResponse>> listarTodos() {
        return ResponseEntity.ok(animalService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(animalService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AnimalResponse> criar(@Valid @RequestBody AnimalRequest animalRequest) {
        return ResponseEntity.status(201).body(animalService.salvar(animalRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody AnimalRequest animalRequest) {
        return ResponseEntity.ok(animalService.atualizar(id, animalRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        animalService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
