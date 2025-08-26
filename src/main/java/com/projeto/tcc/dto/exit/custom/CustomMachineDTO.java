package com.projeto.tcc.dto.exit.custom;

import com.projeto.tcc.enums.StatusMachine;

import java.time.LocalDate;

public record CustomMachineDTO(
        Long id,
        String name,
        Integer serieNumber,
        CustomMachineModelDTO modelMachine,
        StatusMachine status,
        Float oee,
        String photo,
        Integer throughput,
        LocalDate lastMaintenance
) {
}
