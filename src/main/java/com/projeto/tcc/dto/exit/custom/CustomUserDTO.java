package com.projeto.tcc.dto.exit.custom;

import com.projeto.tcc.entities.Role;

import java.util.Set;

public record CustomUserDTO(
        Long id,
        String email,
        String username,
        Set<Role> roles
) {
}
