package com.projeto.tcc.dto.pesquisa;

import com.projeto.tcc.dto.pesquisa.personalizada.MaquinaPersonalizadoDTO;

import java.time.LocalDate;

public record HistoricoAlocacaoFuncionarioMaquinaDTO(
        Long id,
        FuncionarioResultadoDTO funcionario,
        MaquinaPersonalizadoDTO maquinaOrigem,
        MaquinaPersonalizadoDTO maquinaDestino,
        LocalDate dataAlocacao,
        FuncionarioResultadoDTO funcionarioAlocou
) {
}
