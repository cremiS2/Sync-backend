package com.projeto.tcc.dto.entrada;

import jakarta.validation.constraints.*;

import java.util.List;

public record FuncionarioDTO(

        @NotBlank(message = "Por favor, preencha o campo")
        @Size(max = 200, message = "Campo excede o tamanho máximo de 200 caracteres")
        String nome,

        @NotNull(message = "Por favor, preencha o campo")
        @Max(value = 99999, message = "Campo excede o tamanho máximo de 5 dígitos")
        @Min(1000)
        Integer matricula,

        @NotNull(message = "Por favor, preencha o campo")
        Long setor,

        @NotNull(message = "Por favor, preencha o campo")
        @NotEmpty(message = "Por favor, preencha o campo. Tipos: ADMIN, GERENTE, OPERADOR")
        List<String> roles
) {
}
