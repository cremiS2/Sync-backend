package com.projeto.tcc.dto.pesquisa;

import com.projeto.tcc.dto.pesquisa.personalizada.FuncionarioPersonalizadoDTO;

import java.util.List;

public record SetorResultadoDTO(
        Long id,
        String nome,
        String descricao,
        List<FuncionarioPersonalizadoDTO> funcionarios,
        List<MaquinaResultadoDTO> maquinas
) {
}
