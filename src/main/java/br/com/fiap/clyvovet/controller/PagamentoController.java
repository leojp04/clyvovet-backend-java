package br.com.fiap.clyvovet.controller;

import br.com.fiap.clyvovet.dto.pagamento.PagamentoRequest;
import br.com.fiap.clyvovet.dto.pagamento.PagamentoResponse;
import br.com.fiap.clyvovet.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
@Tag(name = "Pagamentos", description = "Gerenciamento de pagamentos de eventos clínicos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    @Operation(summary = "Registrar novo pagamento")
    public ResponseEntity<PagamentoResponse> criar(@Valid @RequestBody PagamentoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.criar(request));
    }

    @GetMapping
    @Operation(summary = "Listar pagamentos com paginação")
    public ResponseEntity<Page<PagamentoResponse>> listarTodos(
            @PageableDefault(size = 10, sort = "dataPagamento") Pageable pageable) {
        return ResponseEntity.ok(pagamentoService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pagamento por ID")
    public ResponseEntity<PagamentoResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(pagamentoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pagamento existente")
    public ResponseEntity<PagamentoResponse> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody PagamentoRequest request) {
        return ResponseEntity.ok(pagamentoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar pagamento")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        pagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}