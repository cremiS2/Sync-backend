package com.projeto.tcc.service.validation;

import com.projeto.tcc.dto.entry.EmployeeDTO;
import com.projeto.tcc.entities.Employee;
import com.projeto.tcc.entities.Role;
import com.projeto.tcc.enums.Shift;
import com.projeto.tcc.enums.StatusEmployee;
import com.projeto.tcc.exceptions.CampoInvalidoException;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.exceptions.ExcedeuQuantidadeException;
import com.projeto.tcc.repository.EmployeeRepository;
import com.projeto.tcc.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeValidation {

    private final EmployeeRepository repository;


    public void validarEntidade(Employee employee, EmployeeDTO dto){
        if(existeBoolean(employee)){
            throw new ConflitoCampoException("Funcionário com employeeID já cadastrado!");
        }

        if(dto.sector() != null){
            if(employee.getSector() == null){
                throw new CampoInvalidoException("sector","Setor não existente");
            }
            if(employee.getSector().checkQuantMaximumEmployee()){
                throw new ExcedeuQuantidadeException("Este setor já está com sua capacidade máxima de funcionários");
            }
        }

        if(dto.user() != null){
            if(employee.getUser() == null){
                throw new CampoInvalidoException("user","User não existente");
            }
            if(repository.existsByUser(employee.getUser())){
                throw new ConflitoCampoException("Funcionário com user já cadastrado!");
            }
        }

        try{
            if(dto.shift() != null){
                employee.setShift(Shift.valueOf(dto.shift().toUpperCase()));
            }
        }catch (IllegalArgumentException e){
            throw new CampoInvalidoException("shift", "O turno informado não existe");
        }

        try{
            if(dto.status() != null){
                employee.setStatus(StatusEmployee.valueOf(dto.status().toUpperCase()));
            }
        }catch (IllegalArgumentException e){
            throw new CampoInvalidoException("status", "O status informado não existe");
        }

    }


    private boolean existeBoolean(Employee employee){
        var procura = repository.findByEmployeeID(employee.getEmployeeID());
        if(employee.getId() == null){
            return procura.isPresent();
        }
        return procura.
                map(p -> !p.getId().equals(employee.getId())
                ).orElse(false);
    }

}
