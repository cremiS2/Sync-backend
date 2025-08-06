package com.projeto.tcc.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponse(int statusErro, String menssagem, List<ErrorField> errosCampos) {

    public static ErrorResponse conflito(String mensagem){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }

    public static ErrorResponse naoRegistrado(String mensagem){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), mensagem, List.of());
    }


}
