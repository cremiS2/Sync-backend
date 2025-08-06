package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.exit.AllocatedEmployeeMachineResultDTO;
import com.projeto.tcc.entities.AllocatedEmployeesMachine;
import com.projeto.tcc.repository.EmployeeRepository;
import com.projeto.tcc.repository.MachineRepositoy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, MachineMapper.class})
public abstract class AllocatedEmployeeMachineMapper {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MachineRepositoy machineRepository;

    @Mapping(target = "employee",
            expression =
                    "java(dto.employee() == null ? null : employeeRepository.findById(dto.employee()).orElse(null))"
    )
    @Mapping(target = "changedEmployee",
            expression =
                    "java(dto.changedEmployee() == null ? null : employeeRepository.findById(dto.changedEmployee()).orElse(null))"
    )
    @Mapping(target = "machine",
            expression =
                    "java(dto.machine() == null ? null : machineRepository.findById(dto.machine()).orElse(null))"
    )
    public abstract AllocatedEmployeesMachine toEntity(AllocatedEmployeeMachineResultDTO dto);

    public abstract AllocatedEmployeeMachineResultDTO toDTO(AllocatedEmployeesMachine entity);

}
