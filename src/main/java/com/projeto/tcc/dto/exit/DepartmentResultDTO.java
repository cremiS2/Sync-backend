package com.projeto.tcc.dto.exit;

import com.projeto.tcc.enums.StatusDepartment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record DepartmentResultDTO(
        Long id,
        String name,
        String description,
        String location,
        BigDecimal budget,
        StatusDepartment status,
        List<SectorResultDTO> sectors

) {
}
