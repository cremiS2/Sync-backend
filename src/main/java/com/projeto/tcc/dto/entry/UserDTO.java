package com.projeto.tcc.dto.entry;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(
        Long id,
        @NotBlank(message = "Por favor, informe o username")
        @Size(max = 100, message = "Excedeu a quantidade permitida de caracteres")
        String username,
        @NotBlank(message = "Por favor, informe o e-mail")
        @Email(message = "Preencha em formato de e-mail")
        String email,
        @NotBlank(message = "Por favor, informe o password")
        String password
        ) {

}
