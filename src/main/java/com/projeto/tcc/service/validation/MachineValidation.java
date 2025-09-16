package com.projeto.tcc.service.validation;

import com.projeto.tcc.dto.entry.MachineDTO;
import com.projeto.tcc.entities.Machine;
import com.projeto.tcc.enums.StatusMachine;
import com.projeto.tcc.exceptions.CampoInvalidoException;
import com.projeto.tcc.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MachineValidation {

    private final MachineRepository repository;

    public void validarEntidade(Machine machine){
        if(existeBoolean(machine)) {
            throw new CampoInvalidoException("numeroSerie", "Máquina com número de série já cadastrada!");
        }
    }


    public void validarInformacoes(Machine machine, MachineDTO dto){
        validarEntidade(machine);
        if(dto.machineModel() != null){
            if(machine.getMachineModel() == null){
                throw new CampoInvalidoException("modeloMaquina","Modelo de máquina não existente");
            }
        }
        if(dto.sector() != null){
            if(machine.getSector() == null){
                throw new CampoInvalidoException("setor", "Setor não existente");
            }
        }

        try{
            if(dto.status() != null){
                StatusMachine.valueOf(dto.status().toUpperCase());
            }
        }catch (IllegalArgumentException e){
            throw new CampoInvalidoException("status", "o status informado não existe");
        }

    }

    private boolean existeBoolean(Machine machine){
       var procura = repository.findBySerieNumber(machine.getSerieNumber());
        if(machine.getId() == null){
            return procura.isPresent();
        }

        return procura.
                map(p -> !p.getId().equals(machine.getId())
                ).orElse(false);
    }


}
