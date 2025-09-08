package com.projeto.tcc.dto.entry;

import jakarta.validation.constraints.*;

import java.util.List;

public record EmployeeDTO(

        @NotBlank(message = "Por favor, preencha o campo")
        @Size(max = 200, message = "Campo excede o tamanho máximo de 200 caracteres")
        String name,

        @NotNull(message = "Por favor, preencha o campo")
        @Max(value = 9999, message = "Não pode passar de 5 dígitos")
        @Min(value = 1000, message = "Não pode ter menos de 5 dígitos")
        Integer employeeID,

        @NotNull(message = "Por favor, preencha o campo")
        Long sector,

        @NotNull(message = "Por favor, preencha o campo")
        @NotEmpty(message = "Por favor, preencha o campo. Tipos: ADMIN, GERENTE, OPERADOR")
        List<String> roles,

        @NotBlank(message = "Por favor, preencha o campo")
        String shift,

        @NotBlank(message = "Por favor, preencha o campo")
        String status,

        @NotBlank(message = "Por favor, preencha o campo")
        String photo,

        @NotNull(message = "Por favor, preencha o campo")
        Long user,

        Boolean availability

) {
}


