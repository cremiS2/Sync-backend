package com.projeto.tcc.dto.pesquisa;

import com.projeto.tcc.enuns.StatusDepartamento;

import java.math.BigDecimal;
import java.util.Set;

public record DepartamentoResultadoDTO(
        Long id,
        String name,
        String description,
        String location,
        BigDecimal budget,
        StatusDepartamento status,
        Set<SetorResultadoDTO> sectors

) {
}
