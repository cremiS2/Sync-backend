package com.projeto.tcc.dto.exit;

import com.projeto.tcc.dto.exit.custom.CustomDepartmentDTO;
import com.projeto.tcc.dto.exit.custom.CustomEmployeeDTO;
import com.projeto.tcc.dto.exit.custom.CustomMachineDTO;
import com.projeto.tcc.entities.Department;

import java.util.List;

public record SectorResultDTO(
        Long id,
        String name,
        String efficiency,
        CustomDepartmentDTO department,
        List<CustomEmployeeDTO> employees,
        List<CustomMachineDTO> machines

) {
}
