package com.projeto.tcc.dto.pesquisa;

import com.projeto.tcc.dto.pesquisa.personalizada.MaquinaPersonalizadoDTO;

import java.util.List;

public record ModeloMaquinaResultadoDTO(
        String nomeModelo,
        String descricao,
        List<MaquinaPersonalizadoDTO> maquinas
) {
}
