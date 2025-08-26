package com.projeto.tcc.dto.entry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepartmentDTO(

        @NotBlank(message = "Por favor, preencha o campo")
        String name,

        @NotNull(message = "Por favor, preencha o campo")
        String description,

        @NotNull(message = "Por favor, preencha o campo")
        String location,

        @NotNull(message = "Por favor, preencha o campo")
        BigDecimal budget,

        @NotNull(message = "Por favor, preencha o campo")
        String status


) {
}
