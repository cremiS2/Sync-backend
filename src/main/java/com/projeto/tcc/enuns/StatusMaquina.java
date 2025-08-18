package com.projeto.tcc.enuns;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum StatusMaquina{
    OPERANDO("operando"),
    PARADA("parada"),
    MANUTENCAO("manutencao");


    private final String nome;
}
