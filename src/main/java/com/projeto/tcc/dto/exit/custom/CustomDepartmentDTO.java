package com.projeto.tcc.dto.exit.custom;

import com.projeto.tcc.enums.StatusDepartment;

import java.math.BigDecimal;

public record CustomDepartmentDTO(
        Long id,
        String name,
        String description,
        String location,
        BigDecimal budget,
        StatusDepartment status
) {
}
