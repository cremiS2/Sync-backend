package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entry.DepartmentDTO;
import com.projeto.tcc.dto.exit.DepartmentResultDTO;
import com.projeto.tcc.entities.Department;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {MachineMapper.class, SectorMapper.class})
public abstract class DepartmentMapper {

    @Mapping(target = "status", ignore = true)
    abstract public Department toEntity(DepartmentDTO dto);

    abstract public DepartmentResultDTO toDTO(Department department);

    @Mapping(target = "status", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract public void updateEntity(@MappingTarget Department department, DepartmentDTO departmentDTO);
}
