package com.projeto.tcc.service.validation;

import com.projeto.tcc.entities.Funcionario;
import com.projeto.tcc.entities.Maquina;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FuncionarioValidation {

    private final FuncionarioRepository repository;


    public void validarEntidade(Funcionario funcionario){
        if(existeBoolean(funcionario)){
            throw new ConflitoCampoException("Máquina com número de série já cadastrada!");
        }
    }


    private boolean existeBoolean(Funcionario funcionario){
        var procura = repository.findByMatricula(funcionario.getMatricula());
        if(funcionario.getId() == null){
            return procura.isPresent();
        }

        return procura.
                map(p -> !p.getId().equals(funcionario.getId())
                ).orElse(false);
    }
}
