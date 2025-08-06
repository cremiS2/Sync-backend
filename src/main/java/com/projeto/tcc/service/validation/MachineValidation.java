package com.projeto.tcc.service.validation;

import com.projeto.tcc.dto.entry.MachineDTO;
import com.projeto.tcc.entities.Machine;
import com.projeto.tcc.enuns.StatusMachine;
import com.projeto.tcc.exceptions.CampoInvalidoException;
import com.projeto.tcc.repository.MachineRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MachineValidation {

    private final MachineRepositoy repositoy;

    public void validarEntidade(Machine machine){
        if(existeBoolean(machine)){
            throw new CampoInvalidoException("numeroSerie","Máquina com número de série já cadastrada!");
        } else if (funcionarioJaAlocado(machine)) {
            throw new CampoInvalidoException("funcionarioOperando","Funcionário já está operando em outra máquina");
        }
    }


    public void validarInformacoes(Machine machine, MachineDTO dto){
        validarEntidade(machine);
        if(dto.localMaquina() != null){
            if(machine.getDepartamentoMaquina() == null){
                throw new CampoInvalidoException("localMaquina", "Local não existente");
            }
        }
        if(dto.modeloMaquina() != null){
            if(machine.getModeloMaquina() == null){
                throw new CampoInvalidoException("modeloMaquina","Modelo de máquina não existente");
            }
        }
        if(dto.setor() != null){
            if(machine.getSetor() == null){
                throw new CampoInvalidoException("setor", "Setor não existente");
            }
        }
        if(dto.funcionarioOperando() != null){
            if(machine.getFuncionarioOperando() == null){
                throw new CampoInvalidoException("funcionarioOperando", "funcionário com id " + dto.funcionarioOperando() + " não encontrado");
            }
        }

        //Arrumar uma forma melhor de fazer isso depois!
        if(dto.status() != null){
            int erro = 0;
            for(StatusMachine status : StatusMachine.values()){
                if(!status.getNome().equals(dto.status().toLowerCase())){
                    erro += 1;
                }
            }
            if(erro == 4){
                throw new CampoInvalidoException("status", "o status informado não existe");
            }
        }


    }



    private boolean existeBoolean(Machine machine){
       var procura = repositoy.findByNumeroSerie(machine.getNumeroSerie());
        if(machine.getId() == null){
            return procura.isPresent();
        }

        return procura.
                map(p -> !p.getId().equals(machine.getId())
                ).orElse(false);
    }

    private boolean funcionarioJaAlocado(Machine machine){
        return machine.getFuncionarioOperando() != null && repositoy.findByFuncionarioOperando(
                        machine.getFuncionarioOperando())
                .isPresent();
    }

}
