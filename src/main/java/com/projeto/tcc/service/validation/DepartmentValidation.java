package com.projeto.tcc.service.validation;

import com.projeto.tcc.dto.entry.DepartmentDTO;
import com.projeto.tcc.entities.Department;
import com.projeto.tcc.enums.StatusDepartment;
import com.projeto.tcc.exceptions.CampoInvalidoException;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class  DepartmentValidation {

    private final DepartmentRepository repository;


    public void validarEntidade(Department department){
        if(existeBoolean(department)){
            throw new ConflitoCampoException("Departamento com nome e/ou local já cadastrado!");
        }

    }

    private boolean existeBoolean(Department department) {
        var procura = repository.findByNameAndLocation(
                department.getName(),
                department.getLocation()
        );

        if (department.getId() == null) {
            return procura.isPresent();
        }

        return procura.
                map(p -> !p.getId().equals(department.getId())
                ).orElse(false);
    }

    public StatusDepartment statusDepartment(DepartmentDTO departmentDTO){
        try{
            return StatusDepartment.valueOf(departmentDTO.status().toUpperCase());
        }catch(IllegalArgumentException e){
            throw new CampoInvalidoException("status", "Status não encontrado!");
        }
    }
}

