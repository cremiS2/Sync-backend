package com.projeto.tcc.dto.entry;

import com.projeto.tcc.enuns.StatusDepartament;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;

public record DepartamentDTO(

        @NotNull(message = "Por favor, preencha o campo")
        String description,

        @NotNull(message = "Por favor, preencha o campo")
        String location,

        @NotNull(message = "Por favor, preencha o campo")
        BigDecimal budget,

        @NotNull(message = "Por favor, preencha o campo")
        StatusDepartament status,

        @NotBlank(message = "Por favor, preencha o campo")
        String name
) {
}
