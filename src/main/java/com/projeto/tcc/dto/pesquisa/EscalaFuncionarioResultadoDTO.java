package com.projeto.tcc.dto.pesquisa;

import com.projeto.tcc.dto.pesquisa.personalizada.FuncionarioPersonalizadoDTO;
import com.projeto.tcc.enuns.Turno;

import java.time.LocalTime;
import java.util.List;

public record EscalaFuncionarioResultadoDTO(
        Long id,
        Turno turnoFuncionario,
        LocalTime horarioInicioJornada,
        LocalTime horarioPausaAlmoco,
        LocalTime horarioRetornoAlmoco,
        LocalTime horarioFimJornada,
        List<FuncionarioPersonalizadoDTO> funcionarios
) {
}
