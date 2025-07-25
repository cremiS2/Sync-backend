package com.projeto.tcc.dto.entrada;

import jakarta.validation.constraints.NotBlank;

public record SetorDTO(

        @NotBlank(message = "Por favor, preencha o campo")
        String nome,

        String descricao
) {
}
