package com.projeto.tcc.dto.exit;

import com.projeto.tcc.dto.exit.custom.CustomMachineDTO;

import java.util.List;

public record MachineModelResultDTO(
        String modelName,
        String modelDescription,
        List<CustomMachineDTO> machines
) {
}
