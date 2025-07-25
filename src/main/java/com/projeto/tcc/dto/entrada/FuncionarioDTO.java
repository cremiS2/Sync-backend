package com.projeto.tcc.dto.entrada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record FuncionarioDTO(

        @NotBlank(message = "Por favor, preencha o campo")
        @Size(max = 200, message = "Campo excede o tamanho máximo de 200 caracteres")
        String nome,

        @NotBlank(message = "Por favor, preencha o campo")
        @Size(max = 5, message = "Campo excede o tamanho máximo de 5 dígitos")
        String matricula,

        @NotNull(message = "Por favor, preencha o campo")
        Long escalaFuncionario,

        @NotNull(message = "Por favor, preencha o campo")
        Long setor,

        @NotNull(message = "Por favor, preencha o campo")
        @NotEmpty(message = "Por favor, preencha o campo. Tipos: ADMIN, GERENTE, OPERADOR")
        List<String> roles
) {
}
