package com.projeto.tcc.dto.exit.custom;

import com.projeto.tcc.enuns.StatusDepartament;

import java.math.BigDecimal;

public record CustomDepartamentDTO(
        Long id,
        String name,
        String description,
        String location,
        BigDecimal budget,
        StatusDepartament status
) {
}
