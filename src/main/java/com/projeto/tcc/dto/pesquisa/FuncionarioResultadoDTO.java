package com.projeto.tcc.dto.pesquisa;

import com.projeto.tcc.dto.entrada.EscalaFuncionarioDTO;
import com.projeto.tcc.dto.pesquisa.personalizada.MaquinaPersonalizadoDTO;
import com.projeto.tcc.dto.pesquisa.personalizada.SetorPersonalizadoDTO;
import com.projeto.tcc.entities.Role;

import java.util.Set;

public record FuncionarioResultadoDTO(
        Long id,
        String nome,
        Integer matricula,
        EscalaFuncionarioDTO escalaFuncionario,
        Set<Role> roles,
        SetorPersonalizadoDTO setor,
        MaquinaPersonalizadoDTO maquinaOperada
) {
}
