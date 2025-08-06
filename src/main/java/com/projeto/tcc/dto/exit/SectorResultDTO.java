package com.projeto.tcc.dto.exit;

import com.projeto.tcc.dto.exit.custom.CustomEmployeeDTO;
import com.projeto.tcc.dto.exit.custom.CustomMachineDTO;
import com.projeto.tcc.entities.Departament;

import java.util.List;

public record SectorResultDTO(
        Long id,
        String name,
        String efficiency,
        Departament departament,
        List<CustomEmployeeDTO> employees,
        List<CustomMachineDTO> machines

) {
}
