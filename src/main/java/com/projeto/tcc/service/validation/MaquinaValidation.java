package com.projeto.tcc.service.validation;

import com.projeto.tcc.dto.ErroCampo;
import com.projeto.tcc.entities.Maquina;
import com.projeto.tcc.exceptions.CampoInvalidoException;
import com.projeto.tcc.repository.MaquinaRepositoy;
import com.projeto.tcc.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MaquinaValidation {

    private final MaquinaRepositoy repositoy;
    private final FuncionarioService funcionarioService;
    private List<ErroCampo> listaErros = new ArrayList<>();



    public void validarEntidade(Maquina maquina){
        if(existeBoolean(maquina)){
            throw new CampoInvalidoException("numeroSerie","Máquina com número de série já cadastrada!");
        } else if (funcionarioJaAlocado(maquina)) {
            throw new CampoInvalidoException("funcionarioOperando","Funcionário já está operando em outra máquina");
        }
        
        if(maquina.getLocalMaquina() == null){
            throw new CampoInvalidoException("localMaquina", "Local não existente");
        }
        if(maquina.getModeloMaquina() == null){
            throw new CampoInvalidoException("modeloMaquina","Modelo de máquina não existente");
        }
        if(maquina.getSetor() == null){
            throw new CampoInvalidoException("setor", "Setor não existente");
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
                        funcionarioService
                                .getFuncionarioEntity(maquina.getFuncionarioOperando().getId()))
                .isPresent();
    }

}
