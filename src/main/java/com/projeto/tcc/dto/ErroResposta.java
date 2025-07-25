package com.projeto.tcc.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int statusErro, String menssagem, List<ErroCampo> errosCampos) {

    public static ErroResposta conflito(String mensagem){
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }

    public static ErroResposta naoRegistrado(String mensagem){
        return new ErroResposta(HttpStatus.NOT_FOUND.value(), mensagem, List.of());
    }


}
