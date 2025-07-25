package com.projeto.tcc.exceptions;

import com.projeto.tcc.dto.ErroCampo;
import com.projeto.tcc.dto.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleCamposInvvalidosExcption(MethodArgumentNotValidException e){
        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação",
                e.getFieldErrors().stream().map(erro -> new ErroCampo(erro.getField(), erro.getDefaultMessage())).toList()
        );
    }

    @ExceptionHandler(ConflitoCampoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleConflitoException(ConflitoCampoException e){
        return ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(NaoRegistradoExcpetion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta handleRegistroNaoEncotradoException(NaoRegistradoExcpetion e){
        return ErroResposta.naoRegistrado(e.getMessage());
    }

    @ExceptionHandler(CampoInvalidoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleCampoInvalidoException(CampoInvalidoException e){
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Informações inválidas", List.of(new ErroCampo(e.campo, e.getMessage())));
    }
}
