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
    EM_MANUTENCAO("em_manutencao"),
    AVARIADA("avariada");

    private final String nome;
}
