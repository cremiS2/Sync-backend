package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entry.SectorDTO;
import com.projeto.tcc.dto.exit.DepartamentResultDTO;
import com.projeto.tcc.dto.exit.SectorResultDTO;
import com.projeto.tcc.entities.Sector;
import com.projeto.tcc.repository.DepartamentRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, MachineMapper.class, DepartamentMapper.class})
public abstract class SectorMapper {

    @Autowired
    DepartamentRepository departamentRepository;

    @Mapping(target = "departament",
        expression = "java(dto.departament() == null ? null : departamentRepository.findById(dto.departament()).orElse(null))"
    )
    public abstract Sector toEntity(SectorDTO dto);

    public abstract SectorResultDTO toDTO(Sector sector);

}
