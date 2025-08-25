package com.projeto.tcc.service.validation;

import com.projeto.tcc.entities.MachineModel;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.MachineModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModeloMaquinasValidation {

    private final MachineModelRepository repository;

    public void validarEntidade(MachineModel machineModel){
        if(existeBoolean(machineModel)){
            throw new ConflitoCampoException("Informações já cadastradas!");
        }
    }

    private boolean existeBoolean(MachineModel machineModel){
        var procura = repository.findByModelNameAndModelDescription(
                machineModel.getModelName(),
                machineModel.getModelDescription()
        );

        if(machineModel.getId() == null){
            return procura.isPresent();
        }

        return procura.
                map(p -> !p.getId().equals(machineModel.getId())
        ).orElse(false);
    }
}
