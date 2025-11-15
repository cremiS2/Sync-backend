package com.projeto.tcc.dto.entry;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordDTO(
        @NotBlank(message = "Por favor, informe o e-mail")
        @Email(message = "Preencha em formato de e-mail")
        String email
) {
}

