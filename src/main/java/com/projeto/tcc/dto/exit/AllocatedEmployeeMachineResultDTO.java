package com.projeto.tcc.dto.exit;

import com.projeto.tcc.dto.exit.custom.CustomEmployeeDTO;
import com.projeto.tcc.dto.exit.custom.CustomMachineDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public record AllocatedEmployeeMachineResultDTO(
        Long id,
        CustomEmployeeDTO employee,
        CustomEmployeeDTO changedEmployee,
        CustomMachineDTO machine,
        LocalTime entryTime,
        LocalTime departureTime,
        LocalDate allocationDate,
        AllocatedEmployeeMachineResultDTO allocatedEmployeeMachineResultDTO
) {
}
