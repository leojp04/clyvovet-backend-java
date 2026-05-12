package br.com.fiap.clyvovet.exception;

import br.com.fiap.clyvovet.dto.exception.ErroValidacao;
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
}
