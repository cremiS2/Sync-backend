package com.projeto.tcc.dto.pesquisa;

import com.projeto.tcc.dto.pesquisa.personalizada.MaquinaPersonalizadoDTO;
import com.projeto.tcc.enuns.Turno;

import java.time.LocalDate;
import java.util.List;

public record ProdutoResultadoDTO(
        String codigoProduto,
        String nomeProduto,
        LocalDate validade,
        Turno turnoProducao,
        List<MaquinaPersonalizadoDTO> maquinas
) {
}
