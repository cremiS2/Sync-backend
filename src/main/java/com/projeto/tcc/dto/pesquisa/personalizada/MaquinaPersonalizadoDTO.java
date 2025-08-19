package com.projeto.tcc.dto.pesquisa.personalizada;

import com.projeto.tcc.dto.nao_utilizadas.ModeloMaquinasDTO;
import com.projeto.tcc.enuns.StatusMaquina;

import java.time.LocalDate;

public record MaquinaPersonalizadoDTO(
        Long id,
        String name,
        Integer serieNumber,
        ModeloMaquinasDTO modelMachine,
        StatusMaquina status,
        Float oee,
        String photo,
        Integer throughput,
        LocalDate lastMaintenance
) {
}
