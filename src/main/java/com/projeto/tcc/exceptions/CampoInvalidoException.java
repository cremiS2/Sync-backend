package com.projeto.tcc.exceptions;

import com.projeto.tcc.dto.ErroCampo;
import lombok.Getter;

import java.util.List;

public class CampoInvalidoException extends RuntimeException {

    @Getter
    String campo;
    public CampoInvalidoException(String campo, String message) {
        super(message);
        this.campo = campo;
    }
}
