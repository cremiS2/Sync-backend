package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.AllocatedEmployeeMachineDTO;
import com.projeto.tcc.dto.mappers.AllocatedEmployeeMachineMapper;
import com.projeto.tcc.entities.AllocatedEmployeeMachine;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.AllocatedEmployeesMachineRepository;
import com.projeto.tcc.service.validation.AllocatedEmployeesMachineValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AllocatedEmployeeMachineService {

    private final AllocatedEmployeesMachineRepository repository;
    private final AllocatedEmployeeMachineMapper mapper;
    private final AllocatedEmployeesMachineValidation validation;
    private final UserService userService;

    public Long createAllocatedEmployees(AllocatedEmployeeMachineDTO dto) {
        AllocatedEmployeeMachine allocatedEmployeeMachine = mapper.toEntity(dto);
        validation.validEntity(allocatedEmployeeMachine);
        allocatedEmployeeMachine.getEmployee().setAvailability(false);
//        Employee changedEmployee = userService.findUser(Long.valueOf(idEmployeeAllocated)).getEmployee();
//        allocatedEmployeeMachine.setEmployee(changedEmployee);
        return repository.save(allocatedEmployeeMachine).getId();
    }


    private AllocatedEmployeeMachine getAllocatedById(Long id){
        return repository.findById(id).orElseThrow(() -> new NaoRegistradoException("Alocação não encontrada"));
    }


}
