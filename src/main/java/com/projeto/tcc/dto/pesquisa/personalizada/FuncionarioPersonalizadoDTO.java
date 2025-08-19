package com.projeto.tcc.dto.pesquisa.personalizada;

import com.projeto.tcc.entities.Role;
import com.projeto.tcc.enuns.StatusFuncionario;
import com.projeto.tcc.enuns.Turno;

import java.util.Set;

public record FuncionarioPersonalizadoDTO(
        Long id,
        String name,
        String photo,
        Turno shift,
        StatusFuncionario status,
        Set<Role> roles
) {
}
