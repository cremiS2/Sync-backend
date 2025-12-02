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

        if(allocatedEmployeeMachine == null){
            throw new NaoRegistradoException("Dados da alocação inválidos");
        }

        if(allocatedEmployeeMachine.getEmployee() == null ){
            throw new NaoRegistradoException("Funcionário não encontrado. Verifique se o ID do funcionário existe.");
        }

        if(allocatedEmployeeMachine.getMachine() == null ){
            throw new NaoRegistradoException("Máquina não encontrada. Verifique se o ID da máquina existe.");
        }

        // Removida a validação obrigatória do changedEmployee
        // pois nem todo usuário que cria atribuição é um funcionário

        if(checkIfExist(allocatedEmployeeMachine)){
            throw new ConflitoCampoException("O funcionário já está alocado em uma máquina");
        }
    }


    private boolean checkIfExist(AllocatedEmployeeMachine allocatedEmployeeMachine) {
        // Verificar se já existe uma alocação ativa para este funcionário no banco
        boolean existsInDatabase = repository.existsByEmployee(allocatedEmployeeMachine.getEmployee());
        
        if (existsInDatabase) {
            return true;
        }

        // Também verificar o campo availability como fallback
        if(allocatedEmployeeMachine.getEmployee().getAvailability() != null){
            return !allocatedEmployeeMachine.getEmployee().getAvailability();
        }
        
        return false;
    }

}
