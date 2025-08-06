package com.projeto.tcc.dto.entry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record AllocatedEmployeeMachineDTO(

        @NotNull(message = "Por favor, preencha o campo")
        Long employee,

        @NotNull(message = "Por favor, preencha o campo")
        Long machine,

        @NotNull(message = "Por favor, preencha o campo")
        Long chagedEmployee,

        @NotBlank(message = "Por favor, preencha o campo")
        LocalTime allocationDate,

        @NotBlank(message = "Por favor, preencha o campo")
        LocalTime departureTime

) {
}
