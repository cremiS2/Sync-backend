package com.projeto.tcc.service.validation;

import com.projeto.tcc.dto.entry.EmployeeDTO;
import com.projeto.tcc.entities.Employee;
import com.projeto.tcc.entities.Role;
import com.projeto.tcc.exceptions.CampoInvalidoException;
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
    private final RoleRepository roleRepository;


    public void validarEntidade(Employee employee, EmployeeDTO dto){
        if(existeBoolean(employee)){
            throw new CampoInvalidoException("matricula", "Funcionário com matrícula já cadastrado!");
        }

        if(dto.sector() != null){
            if(employee.getSector() == null){
                throw new CampoInvalidoException("setor","Setor não existente");
            }
        }
        //Se colocar mais de uma role, ele não identifica se as outras roles existem!
       if(dto.roles() != null && !dto.roles().isEmpty()){
           Set<Role> rolesFuncionario = roleRepository.findAllByNameIn(dto.roles().stream().map(String::toLowerCase).collect(Collectors.toSet()));
           if (rolesFuncionario.isEmpty()) throw new CampoInvalidoException("roles","Roles não encontradas");
           employee.setRoles(rolesFuncionario);
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
