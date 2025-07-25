package com.projeto.tcc.service.validation;

import com.projeto.tcc.entities.Local;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.LocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalValidation {

    private final LocalRepository repository;

    public void validarEntidade(Local local){
        if(existeBoolean(local)){
            throw new ConflitoCampoException("Informações já cadastradas!");
        }
    }

    private boolean existeBoolean(Local local) {
        var procura = repository.findByEnderecoAndNomeUnidadeAndCapacidadeMaquinas(
                local.getEndereco(),
                local.getNomeUnidade(),
                local.getCapacidadeMaquinas()
        );

        if (local.getId() == null) {
            return procura.isPresent();
        }

        return procura.
                map(p -> !p.getId().equals(local.getId())
                ).orElse(false);
    }
}

