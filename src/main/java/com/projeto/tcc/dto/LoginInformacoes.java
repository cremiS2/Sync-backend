package com.projeto.tcc.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginInformacoes(

        @Email(message = "Por favor, preencha em formato de e-mail")
        @NotBlank(message = "Por favor, preencha o campo")
        String email,

        @NotBlank(message = "Por favor, preencha o campo")
        String password
) {
}
