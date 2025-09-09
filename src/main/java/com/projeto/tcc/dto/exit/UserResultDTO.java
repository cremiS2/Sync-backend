package com.projeto.tcc.dto.exit;

import com.projeto.tcc.dto.exit.custom.CustomEmployeeDTO;

public record UserResultDTO(
        Long id,
        String email,
        String username,
        CustomEmployeeDTO employee
) {
}
