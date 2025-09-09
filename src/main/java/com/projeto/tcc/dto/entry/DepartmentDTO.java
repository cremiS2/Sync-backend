package com.projeto.tcc.dto.entry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepartmentDTO(

        @NotBlank(message = "Por favor, preencha o campo")
        String name,

        @NotBlank(message = "Por favor, preencha o campo")
        String description,

        @NotBlank(message = "Por favor, preencha o campo")
        String location,

        @NotNull(message = "Por favor, preencha o campo")
        BigDecimal budget,

        @NotBlank(message = "Por favor, preencha o campo")
        String status


) {
}
