package com.projeto.tcc.dto.exit;

import com.projeto.tcc.dto.exit.custom.CustomSectorDTO;
import com.projeto.tcc.dto.exit.custom.CustomUserDTO;
import com.projeto.tcc.entities.Role;
import com.projeto.tcc.enuns.Shift;
import com.projeto.tcc.enuns.StatusEmployee;

import java.util.Set;

public record EmployeeResultDTO(
        Long id,
        Integer employeeID,
        String name,
        String photo,
        Shift shift,
        Set<Role> roles,
        CustomSectorDTO sector,
        StatusEmployee status,
        Boolean availability,
        CustomUserDTO user
) {
}
