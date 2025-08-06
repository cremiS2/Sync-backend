package com.projeto.tcc.dto.exit;

import com.projeto.tcc.dto.exit.custom.CustomEmployeeDTO;
import com.projeto.tcc.dto.exit.custom.CustomMachineModelDTO;
import com.projeto.tcc.dto.exit.custom.CustomSectorDTO;
import com.projeto.tcc.enuns.StatusMachine;

import java.time.LocalDate;

public record MachineResultDTO(
        Long id,
        String name,
        Integer serieNumber,
        CustomMachineModelDTO modelMachine,
        StatusMachine status,
        Float oee,
        String photo,
        Integer throughput,
        LocalDate lastMaintence,
        CustomSectorDTO sector
) {
}
