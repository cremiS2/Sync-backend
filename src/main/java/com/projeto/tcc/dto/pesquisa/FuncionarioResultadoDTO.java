package com.projeto.tcc.dto.pesquisa;

import com.projeto.tcc.dto.nao_utilizadas.EscalaFuncionarioDTO;
import com.projeto.tcc.dto.pesquisa.personalizada.MaquinaPersonalizadoDTO;
import com.projeto.tcc.dto.pesquisa.personalizada.SetorPersonalizadoDTO;
import com.projeto.tcc.entities.Role;
import com.projeto.tcc.enuns.StatusFuncionario;
import com.projeto.tcc.enuns.Turno;

import java.util.Set;

public record FuncionarioResultadoDTO(
        Long id,
        String name,
        String photo,
        Turno shift,
        Set<Role> roles,
        SetorPersonalizadoDTO sector,
        MaquinaPersonalizadoDTO machine,
        StatusFuncionario status
) {
}
