package com.projeto.tcc.dto.entry;

import jakarta.validation.constraints.NotNull;

public record AllocatedEmployeeMachineDTO(

        @NotNull(message = "Por favor, preencha o campo")
        Long employee,

        @NotNull(message = "Por favor, preencha o campo")
        Long machine

        ) {
}
