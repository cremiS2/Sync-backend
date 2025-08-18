package com.projeto.tcc.service.validation;

import com.projeto.tcc.entities.Departamento;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.LocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalValidation {

    private final LocalRepository repository;

    public void validarEntidade(Departamento departamento){
        if(existeBoolean(departamento)){
            throw new ConflitoCampoException("Informações já cadastradas!");
        }
    }

    private boolean existeBoolean(Departamento departamento) {
        var procura = repository.findByEnderecoAndNomeUnidadeAndCapacidadeMaquinas(
                departamento.getEndereco(),
                departamento.getNomeDepartamento(),
                departamento.getCapacidadeMaquinas()
        );

        if (departamento.getId() == null) {
            return procura.isPresent();
        }

        return procura.
                map(p -> !p.getId().equals(departamento.getId())
                ).orElse(false);
    }
}

