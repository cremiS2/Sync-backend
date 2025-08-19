package com.projeto.tcc.dto.pesquisa.personalizada;

import com.projeto.tcc.enuns.StatusDepartamento;

import java.math.BigDecimal;

public record DepartamentoPersonalizadoDTO(
        Long id,
        String name,
        String description,
        String location,
        BigDecimal budget,
        StatusDepartamento status
) {
}
