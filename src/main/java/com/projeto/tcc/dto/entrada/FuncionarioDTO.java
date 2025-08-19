package com.projeto.tcc.dto.entrada;

import com.projeto.tcc.enuns.StatusFuncionario;
import com.projeto.tcc.enuns.Turno;
import jakarta.validation.constraints.*;

import java.util.List;

public record FuncionarioDTO(

        @NotBlank(message = "Por favor, preencha o campo")
        @Size(max = 200, message = "Campo excede o tamanho máximo de 200 caracteres")
        String name,

        @NotNull(message = "Por favor, preencha o campo")
        Long sector,

        @NotNull(message = "Por favor, preencha o campo")
        @NotEmpty(message = "Por favor, preencha o campo. Tipos: ADMIN, GERENTE, OPERADOR")
        List<String> roles,

        @NotNull(message = "Por favor, preencha o campo")
        Turno shift,

        @NotNull(message = "Por favor, preencha o campo")
        String status,

        @NotNull(message = "Por favor, preencha o campo")
        String photo

) {
}


