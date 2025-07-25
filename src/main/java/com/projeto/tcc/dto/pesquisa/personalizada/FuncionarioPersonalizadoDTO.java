package com.projeto.tcc.dto.pesquisa.personalizada;

import com.projeto.tcc.entities.Role;

import java.util.Set;

public record FuncionarioPersonalizadoDTO(
        Long id,
        String nome,
        String matricula,
        Set<Role> roles
) {
}
