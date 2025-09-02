package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entry.SectorDTO;
import com.projeto.tcc.dto.exit.SectorResultDTO;
import com.projeto.tcc.entities.Sector;
import com.projeto.tcc.repository.DepartmentRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, MachineMapper.class, DepartmentMapper.class})
public abstract class SectorMapper {

    @Autowired
    DepartmentRepository departamentRepository;

    @Mapping(target = "department",
        expression = "java(dto.department() == null ? null : departamentRepository.findById(dto.department()).orElse(null))"
    )
    public abstract Sector toEntity(SectorDTO dto);

    public abstract SectorResultDTO toDTO(Sector sector);


    @Mapping(target = "department",
            expression = "java(dto.department() == null ? null : departamentRepository.findById(dto.department()).orElse(null))"
    )
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract  void updateEnity(@MappingTarget Sector sector, SectorDTO dto);

}
