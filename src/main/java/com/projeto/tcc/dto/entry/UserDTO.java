package com.projeto.tcc.dto.entry;

import jakarta.validation.constraints.*;

import java.util.List;

public record UserDTO(

        @NotBlank(message = "Por favor, informe o e-mail")
        @Email(message = "Preencha em formato de e-mail")
        String email,

        @NotBlank(message = "Por favor, informe o password")
        String password,

        @NotNull(message = "Por favor, preencha o campo")
        @NotEmpty(message = "Por favor, preencha o campo. Tipos: ADMIN, GERENTE, OPERADOR")
        List<String> roles
        ) {

}
