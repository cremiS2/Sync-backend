package com.projeto.tcc.dto.pesquisa.personalizada;

import com.projeto.tcc.dto.entrada.LocalDTO;
import com.projeto.tcc.dto.entrada.ModeloMaquinasDTO;

public record MaquinaPersonalizadoDTO(
        Long id,
        String nomeMaquina,
        Integer numeroSerie,
        ModeloMaquinasDTO modeloMaquina
) {
}
