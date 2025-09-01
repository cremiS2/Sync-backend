package com.projeto.tcc.service.validation;

import com.projeto.tcc.dto.entry.SectorDTO;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SectorValidation {

    @Autowired
    private final DepartmentRepository departmentRepository;

    public void validar(SectorDTO dto){
        if(existeSetor(dto)){
            throw new ConflitoCampoException("Setor com as informações fornecidas já cadastradp");
        }
    }

    private boolean existeSetor(SectorDTO dto){
        return true;
    }
}
