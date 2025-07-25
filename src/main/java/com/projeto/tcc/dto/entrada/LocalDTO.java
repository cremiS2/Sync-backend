package com.projeto.tcc.dto.entrada;

import com.projeto.tcc.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocalDTO(
        @NotNull(message = "Por favor, preencha o campo")
        Endereco endereco,

        @NotBlank(message = "Por favor, preencha o campo")
        String nomeUnidade,

        @NotNull(message = "Por favor, preencha o campo")
        Integer capacidadeMaquinas
) {
}
