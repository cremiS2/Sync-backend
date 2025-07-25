package com.projeto.tcc.dto.pesquisa;

import com.projeto.tcc.dto.pesquisa.personalizada.MaquinaPersonalizadoDTO;
import com.projeto.tcc.entities.Endereco;

import java.util.List;

public record LocalResultadoDTO(
        Long id,
        Endereco endereco,
        String nomeUnidade,
        Integer capacidadeMaquinas,
        List<MaquinaPersonalizadoDTO> maquinasLocal
) {
}
