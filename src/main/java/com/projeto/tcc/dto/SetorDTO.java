package com.projeto.tcc.dto;

import com.projeto.tcc.entities.Funcionario;
import com.projeto.tcc.entities.Setor;

public record SetorDTO(Long id, String nome, String descricao) {

    public SetorDTO(Setor setor){
        this(setor.getId(), setor.getNome(), setor.getDescricao());
    }
}
