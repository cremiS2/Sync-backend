package com.projeto.tcc.dto.entrada;

import com.projeto.tcc.enuns.StatusDepartamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;

public record DepartamentoDTO(

        @NotNull(message = "Por favor, preencha o campo")
        String description,

        @NotNull(message = "Por favor, preencha o campo")
        String location,

        @NotNull(message = "Por favor, preencha o campo")
        BigDecimal budget,

        @NotNull(message = "Por favor, preencha o campo")
        StatusDepartamento status,

        @NotBlank(message = "Por favor, preencha o campo")
        String name,

        Set<Integer> employees,

        Set<Integer> sectors
) {
}
