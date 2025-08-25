package com.projeto.tcc.dto.entry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record AllocatedEmployeeMachineDTO(

        @NotNull(message = "Por favor, preencha o campo")
        Long employee,

        @NotNull(message = "Por favor, preencha o campo")
        Long machine,

        @NotNull(message = "Por favor, preencha o campo")
        Long changedEmployee,

        @NotBlank(message = "Por favor, preencha o campo")
        LocalDate allocationDate,

        @NotBlank(message = "Por favor, preencha o campo")
        LocalTime departureTime

) {
}
