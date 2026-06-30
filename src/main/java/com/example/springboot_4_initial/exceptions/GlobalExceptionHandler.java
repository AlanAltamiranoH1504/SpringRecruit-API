package com.example.springboot_4_initial.exceptions;

import com.example.springboot_4_initial.services.interfaces.IExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final IExceptionService iExceptionService;

    @ExceptionHandler(ListEmptyException.class)
    public ResponseEntity<?> habdleListEmptyException(ListEmptyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                iExceptionService.generateMessage(false, ex.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> json = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            json.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(json);
    }

    @ExceptionHandler(NotFoundEntity.class)
    public ResponseEntity<?> handleNotFoundEntity(NotFoundEntity ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                iExceptionService.generateMessage(false, ex.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                iExceptionService.generateMessage(false, "Tipo de Datos de Argumentos Incorrectos")
        );
    }

    @ExceptionHandler(UpdateException.class)
    public ResponseEntity<?> handleUpdateException(UpdateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                iExceptionService.generateMessage(false, ex.getMessage())
        );
    }
}
