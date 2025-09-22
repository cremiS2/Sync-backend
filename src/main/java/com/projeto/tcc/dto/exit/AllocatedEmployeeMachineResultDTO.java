package com.projeto.tcc.dto.exit;

import com.projeto.tcc.dto.exit.custom.CustomEmployeeDTO;
import com.projeto.tcc.dto.exit.custom.CustomMachineDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AllocatedEmployeeMachineResultDTO(
        Long id,
        CustomEmployeeDTO employee,
        CustomEmployeeDTO changedEmployee,
        LocalTime entryTime,
        LocalTime departureTime,
        LocalDate allocationDate,
        CustomMachineDTO machine
) {
}
