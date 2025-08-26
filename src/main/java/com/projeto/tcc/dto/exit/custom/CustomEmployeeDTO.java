package com.projeto.tcc.dto.exit.custom;

import com.projeto.tcc.entities.Role;
import com.projeto.tcc.enums.Shift;
import com.projeto.tcc.enums.StatusEmployee;

import java.util.Set;

public record CustomEmployeeDTO(
        Long id,
        String name,
        String photo,
        Shift shift,
        StatusEmployee status,
        Set<Role> roles
) {
}
