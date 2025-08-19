package com.projeto.tcc.dto.pesquisa;

import com.projeto.tcc.dto.pesquisa.personalizada.FuncionarioPersonalizadoDTO;
import com.projeto.tcc.dto.pesquisa.personalizada.MaquinaPersonalizadoDTO;
import com.projeto.tcc.dto.pesquisa.personalizada.SetorPersonalizadoDTO;

import java.util.List;

public record SetorResultadoDTO(
        Long id,
        String name,
        String efficiency,
        List<FuncionarioPersonalizadoDTO> employees,
        List<MaquinaPersonalizadoDTO> machines
) {
}
