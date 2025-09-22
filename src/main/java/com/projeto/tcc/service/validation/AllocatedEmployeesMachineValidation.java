package com.projeto.tcc.service.validation;

import com.projeto.tcc.entities.AllocatedEmployeeMachine;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.AllocatedEmployeesMachineRepository;
import com.projeto.tcc.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AllocatedEmployeesMachineValidation {

    private final AllocatedEmployeesMachineRepository repository;
    private final EmployeeService employeeService;

    public void validEntity(AllocatedEmployeeMachine allocatedEmployeeMachine){

        if(checkIfExist(allocatedEmployeeMachine)){
            throw new ConflitoCampoException("O funcionário já está alocado em uma máquina");
        }
    }


    private boolean checkIfExist(AllocatedEmployeeMachine allocatedEmployeeMachine) {
        Boolean alocado = allocatedEmployeeMachine.getEmployee().getAvailability();

        if(alocado != null) {
            return !alocado;
        }

        return false;
    }

}
