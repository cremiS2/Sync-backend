package com.projeto.tcc.dto.exit;

import com.projeto.tcc.enuns.StatusDepartament;

import java.math.BigDecimal;
import java.util.Set;

public record DepartamentResultDTO(
        Long id,
        String name,
        String description,
        String location,
        BigDecimal budget,
        StatusDepartament status,
        Set<SectorResultDTO> sectors

) {
}
