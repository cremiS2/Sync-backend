package com.projeto.tcc.service.validation;

import com.projeto.tcc.dto.entry.MachineModelDTO;
import com.projeto.tcc.entities.MachineModel;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.MachineModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MachineModelValidation {

    private final MachineModelRepository repository;

    public void validarEntidade(MachineModel machineModel, MachineModelDTO dto){
        if(existeBoolean(machineModel)){
            throw new ConflitoCampoException("Modelo de máquina já cadastrado!");
        }
    }

    private boolean existeBoolean(MachineModel machineModel){
        var procura = repository.findByModelName(
                machineModel.getModelName()
        );

        if(machineModel.getId() == null){
            return procura.isPresent();
        }

        return procura.
                map(p -> !p.getId().equals(machineModel.getId())
        ).orElse(false);
    }
}
