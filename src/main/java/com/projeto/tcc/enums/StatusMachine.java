package com.projeto.tcc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusMachine {
    OPERANDO("operando"),
    PARADA("parada"),
    MANUTENCAO("manutencao");


    private final String nome;
}
