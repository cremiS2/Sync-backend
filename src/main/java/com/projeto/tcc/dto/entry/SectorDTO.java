package com.projeto.tcc.dto.entry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SectorDTO(

        @NotBlank(message = "Por favor, preencha o campo")
        String name,

        @NotNull(message = "Por favor, preencha o campo")
        Float efficiency,

        @NotNull(message = "Por favor, preencha o campo")
        Long department
) {
}
