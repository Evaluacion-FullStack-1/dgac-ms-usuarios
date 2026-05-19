package cl.dgac.usuarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(RecursoNoEncontradoException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("fecha", LocalDateTime.now());
        error.put("mensaje", ex.getMessage());
        error.put("estado", HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EmailYaExisteException.class)
    public ResponseEntity<Map<String, Object>> manejarEmailRepetido(EmailYaExisteException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("fecha", LocalDateTime.now());
        error.put("mensaje", ex.getMessage());
        error.put("estado", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, Object> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errores);
    }
}