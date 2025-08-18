package com.projeto.tcc.service.validation;

import com.projeto.tcc.dto.entrada.MaquinaDTO;
import com.projeto.tcc.entities.Maquina;
import com.projeto.tcc.enuns.StatusMaquina;
import com.projeto.tcc.exceptions.CampoInvalidoException;
import com.projeto.tcc.repository.MaquinaRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaquinaValidation {

    private final MaquinaRepositoy repositoy;

    public void validarEntidade(Maquina maquina){
        if(existeBoolean(maquina)){
            throw new CampoInvalidoException("numeroSerie","Máquina com número de série já cadastrada!");
        } else if (funcionarioJaAlocado(maquina)) {
            throw new CampoInvalidoException("funcionarioOperando","Funcionário já está operando em outra máquina");
        }
    }


    public void validarInformacoes(Maquina maquina, MaquinaDTO dto){
        validarEntidade(maquina);
        if(dto.localMaquina() != null){
            if(maquina.getDepartamentoMaquina() == null){
                throw new CampoInvalidoException("localMaquina", "Local não existente");
            }
        }
        if(dto.modeloMaquina() != null){
            if(maquina.getModeloMaquina() == null){
                throw new CampoInvalidoException("modeloMaquina","Modelo de máquina não existente");
            }
        }
        if(dto.setor() != null){
            if(maquina.getSetor() == null){
                throw new CampoInvalidoException("setor", "Setor não existente");
            }
        }
        if(dto.funcionarioOperando() != null){
            if(maquina.getFuncionarioOperando() == null){
                throw new CampoInvalidoException("funcionarioOperando", "funcionário com id " + dto.funcionarioOperando() + " não encontrado");
            }
        }

        //Arrumar uma forma melhor de fazer isso depois!
        if(dto.status() != null){
            int erro = 0;
            for(StatusMaquina status : StatusMaquina.values()){
                if(!status.getNome().equals(dto.status().toLowerCase())){
                    erro += 1;
                }
            }
            if(erro == 4){
                throw new CampoInvalidoException("status", "o status informado não existe");
            }
        }


    }



    private boolean existeBoolean(Maquina maquina){
       var procura = repositoy.findByNumeroSerie(maquina.getNumeroSerie());
        if(maquina.getId() == null){
            return procura.isPresent();
        }

        return procura.
                map(p -> !p.getId().equals(maquina.getId())
                ).orElse(false);
    }

    private boolean funcionarioJaAlocado(Maquina maquina){
        return maquina.getFuncionarioOperando() != null && repositoy.findByFuncionarioOperando(
                        maquina.getFuncionarioOperando())
                .isPresent();
    }

}
