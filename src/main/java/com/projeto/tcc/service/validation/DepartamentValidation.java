package com.projeto.tcc.service.validation;

import com.projeto.tcc.entities.Departament;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.DepartamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class  DepartamentValidation {

    private final DepartamentRepository repository;

    public void validarEntidade(Departament departament){
        if(existeBoolean(departament)){
            throw new ConflitoCampoException("Informações já cadastradas!");
        }
    }

    private boolean existeBoolean(Departament departament) {
        var procura = repository.findByNameAndLocation(
                departament.getName(),
                departament.getLocation()
        );

        if (departament.getId() == null) {
            return procura.isPresent();
        }

        return procura.
                map(p -> !p.getId().equals(departament.getId())
                ).orElse(false);
    }
}

