package com.projeto.tcc.dto.entry;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResetPasswordDTO(
        @NotBlank(message = "Por favor, informe o e-mail")
        @Email(message = "Preencha em formato de e-mail")
        String email,

        @NotBlank(message = "Por favor, informe a nova senha")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String newPassword
) {
}

