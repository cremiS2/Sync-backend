package com.projeto.tcc.service.validation;

import com.projeto.tcc.entities.EscalaFuncionario;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.EscalaFuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EscalaValidation {

    private final EscalaFuncionarioRepository repository;


    public void validarEntidade(EscalaFuncionario escalaFuncionario){
        if(existeEscala(escalaFuncionario)){
            throw new ConflitoCampoException("A escala já existe");
        }
    }

    private boolean existeEscala(EscalaFuncionario escalaFuncionario){
        var escalaProcura = repository.findByTurnoFuncionarioAndHorarioInicioJornadaAndHorarioPausaAlmocoAndHorarioRetornoAlmocoAndHorarioFimJornada(
                escalaFuncionario.getTurnoFuncionario(),
                escalaFuncionario.getHorarioInicioJornada(),
                escalaFuncionario.getHorarioPausaAlmoco(),
                escalaFuncionario.getHorarioRetornoAlmoco(),
                escalaFuncionario.getHorarioFimJornada()
        );

        if(escalaFuncionario.getId() == null){
            return escalaProcura.isPresent();
        }

        return escalaProcura.map(
                e -> e.getId().equals(escalaFuncionario.getId())
        ).orElse(false);
    }
}
