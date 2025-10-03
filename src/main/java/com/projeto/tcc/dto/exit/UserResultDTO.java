package com.projeto.tcc.dto.exit;

import com.projeto.tcc.dto.exit.custom.CustomEmployeeDTO;
import com.projeto.tcc.entities.Role;

import java.util.Set;

public record UserResultDTO(
        Long id,
        String email,
        String username,
        Set<Role> roles,
        CustomEmployeeDTO employee
) {
}
