package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.AllocatedEmployeeMachineDTO;
import com.projeto.tcc.dto.mappers.AllocatedEmployeeMachineMapper;
import com.projeto.tcc.entities.AllocatedEmployeeMachine;
import com.projeto.tcc.repository.AllocatedEmployeesMachineRepository;
import com.projeto.tcc.service.validation.AllocatedEmployeesMachineValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AllocatedEmployeeMachineService {

    private final AllocatedEmployeesMachineRepository allocatedEmployeesMachineRepository;
    private final AllocatedEmployeeMachineMapper mapper;
    private final AllocatedEmployeesMachineValidation validation;

    public Long createAllocatedEmployees(AllocatedEmployeeMachineDTO dto){
        AllocatedEmployeeMachine allocatedEmployeeMachine = mapper.toEntity(dto);
        System.out.println(allocatedEmployeeMachine.getEmployee().getName());
        validation.validEntity(allocatedEmployeeMachine);
        allocatedEmployeeMachine.getEmployee().setAvailability(true);
        return allocatedEmployeesMachineRepository.save(allocatedEmployeeMachine).getId();
    }


}
