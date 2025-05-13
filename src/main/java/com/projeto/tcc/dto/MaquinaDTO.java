package com.projeto.tcc.dto;

import com.projeto.tcc.entities.Maquina;
import com.projeto.tcc.entities.Setor;
import com.projeto.tcc.enuns.StatusMaquina;

import java.time.LocalDate;

public record MaquinaDTO(Long id, String nome, LocalDate ultima_manutencao, Setor setor, StatusMaquina ativo) {

    public MaquinaDTO(Maquina maquina){
        this(maquina.getId(), maquina.getNome(), maquina.getUltima_manutencao(),maquina.getSetor(), maquina.getStatus());
    }

}
