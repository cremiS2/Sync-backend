package com.projeto.tcc.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.projeto.tcc.dto.ErrorField;
import com.projeto.tcc.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleCamposInvvalidosExcption(MethodArgumentNotValidException e){
        return new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação",
                e.getFieldErrors().stream().map(erro -> new ErrorField(erro.getField(), erro.getDefaultMessage())).toList()
        );
    }

    @ExceptionHandler(ConflitoCampoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflitoException(ConflitoCampoException e){
        return ErrorResponse.conflito(e.getMessage());
    }

    @ExceptionHandler(NaoRegistradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleRegistroNaoEncotradoException(NaoRegistradoException e){
        return ErrorResponse.naoRegistrado(e.getMessage());
    }

    @ExceptionHandler(CampoInvalidoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleCampoInvalidoException(CampoInvalidoException e){
        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Informações inválidas", List.of(new ErrorField(e.campo, e.getMessage())));
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ErrorResponse handleInvalidFormatException(InvalidFormatException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Formato inválido", List.of(new ErrorField("Só aceita números", ex.getPath().getFirst().getFieldName())));
    }
}
