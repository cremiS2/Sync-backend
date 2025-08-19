package com.projeto.tcc.dto.pesquisa;

import com.projeto.tcc.dto.entrada.DepartamentoDTO;
import com.projeto.tcc.dto.nao_utilizadas.ModeloMaquinasDTO;
import com.projeto.tcc.dto.pesquisa.personalizada.FuncionarioPersonalizadoDTO;
import com.projeto.tcc.enuns.StatusMaquina;

import java.time.LocalDate;

public record MaquinaResultadoDTO(
        Long id,
        String name,
        Integer serieNumber,
        ModeloMaquinasDTO modelMachine,
        StatusMaquina status,
        Float oee,
        String photo,
        Integer throughput,
        LocalDate lastMaintence,
        SetorResultadoDTO sector,
        FuncionarioPersonalizadoDTO employess
) {
}
