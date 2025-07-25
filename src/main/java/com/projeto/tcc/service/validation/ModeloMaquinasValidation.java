package com.projeto.tcc.service.validation;

import com.projeto.tcc.entities.ModeloMaquinas;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.ModeloMaquinasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModeloMaquinasValidation {

    private final ModeloMaquinasRepository repository;

    public void validarEntidade(ModeloMaquinas modeloMaquinas){
        if(existeBoolean(modeloMaquinas)){
            throw new ConflitoCampoException("Informações já cadastradas!");
        }
    }

    private boolean existeBoolean(ModeloMaquinas modeloMaquinas){
        var procura = repository.findByNomeModeloAndDescricaoModelo(
                modeloMaquinas.getNomeModelo(),
                modeloMaquinas.getDescricaoModelo()
        );

        if(modeloMaquinas.getId() == null){
            return procura.isPresent();
        }

        return procura.
                map(p -> !p.getId().equals(modeloMaquinas.getId())
        ).orElse(false);
    }
}
