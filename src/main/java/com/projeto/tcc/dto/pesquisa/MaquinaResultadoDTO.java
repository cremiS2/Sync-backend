package com.projeto.tcc.dto.pesquisa;

import com.projeto.tcc.dto.entrada.LocalDTO;
import com.projeto.tcc.dto.entrada.ModeloMaquinasDTO;
import com.projeto.tcc.dto.pesquisa.personalizada.FuncionarioPersonalizadoDTO;
import com.projeto.tcc.enuns.StatusMaquina;

import java.time.LocalDate;

public record MaquinaResultadoDTO(
        Long id,
        String nomeMaquina,
        Integer numeroSerie,
        LocalDate ultimaManutencao,
        StatusMaquina status,
        ModeloMaquinasDTO modeloMaquina,
        LocalDTO localMaquina,
        FuncionarioPersonalizadoDTO funcionarioOperando
) {
}
