package com.projeto.tcc.dto;

import com.projeto.tcc.entities.Maquina;

import java.time.LocalDate;

public record MaquinaDTO(String nome, LocalDate ultima_manutencao) {

    public MaquinaDTO(Maquina maquina){
        this(maquina.getNome(), maquina.getUltima_manutencao());
    }

}
