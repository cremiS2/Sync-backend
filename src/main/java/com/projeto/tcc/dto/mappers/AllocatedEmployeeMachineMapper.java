package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entry.AllocatedEmployeeMachineDTO;
import com.projeto.tcc.dto.exit.AllocatedEmployeeMachineResultDTO;
import com.projeto.tcc.entities.AllocatedEmployeeMachine;
import com.projeto.tcc.repository.EmployeeRepository;
import com.projeto.tcc.repository.MachineRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, MachineMapper.class})
public abstract class AllocatedEmployeeMachineMapper {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MachineRepository machineRepository;

    @Mapping(target = "employee",
            expression =
                    "java(dto.employee() == null ? null : employeeRepository.findById(dto.employee()).orElse(null))"
    )
    @Mapping(target = "machine",
            expression =
                    "java(dto.machine() == null ? null : machineRepository.findById(dto.machine()).orElse(null))"
    )
    public abstract AllocatedEmployeeMachine toEntity(AllocatedEmployeeMachineDTO dto);

    public abstract AllocatedEmployeeMachineResultDTO toDTO(AllocatedEmployeeMachine entity);


    @Mapping(target = "employee",
            expression =
                    "java(dto.employee() == null ? null : employeeRepository.findById(dto.employee()).orElse(null))"
    )
    @Mapping(target = "machine",
            expression =
                    "java(dto.machine() == null ? entity.getMachine() : machineRepository.findById(dto.machine()).orElse(null))"
    )
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget AllocatedEmployeeMachine entity, AllocatedEmployeeMachineDTO dto);

}
