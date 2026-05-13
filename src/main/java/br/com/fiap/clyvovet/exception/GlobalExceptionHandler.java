package br.com.fiap.clyvovet.exception;

import br.com.fiap.clyvovet.dto.exception.ErroValidacao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacao>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErroValidacao> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErroValidacao(error.getField(), error.getDefaultMessage()))
                .toList();
        return ResponseEntity.badRequest().body(erros);
    }

    // Captura EntityNotFoundException e retorna 404 com mensagem legível
    // Sem isso, o Spring retorna 500 por padrão quando a entidade não é encontrada
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroValidacao> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErroValidacao("id", ex.getMessage()));
    }
}