package com.projeto.tcc.dto.pesquisa;

import com.projeto.tcc.dto.entrada.FuncionarioDTO;
import com.projeto.tcc.dto.entrada.MaquinaDTO;
import com.projeto.tcc.enuns.StatusMaquina;

import java.time.LocalDate;

public record HistoricoAlteracaoEstadoMaquinaResultadoDTO(
        Long id,
        LocalDate dataAlteracao,
        StatusMaquina statusAntigo,
        StatusMaquina statusNovo,
        MaquinaResultadoDTO maquina,
        FuncionarioDTO funcionarioAlterou
) {
}
