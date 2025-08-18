package com.projeto.tcc.service.validation;

import com.projeto.tcc.entities.Turno;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.EscalaFuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EscalaValidation {

    private final EscalaFuncionarioRepository repository;


    public void validarEntidade(Turno turno){
        if(existeEscala(turno)){
            throw new ConflitoCampoException("A escala já existe");
        }
    }

    private boolean existeEscala(Turno turno){
        var escalaProcura = repository.findByTurnoFuncionarioAndHorarioInicioJornadaAndHorarioPausaAlmocoAndHorarioRetornoAlmocoAndHorarioFimJornada(
                turno.getTurnoFuncionario(),
                turno.getHorarioInicioJornada(),
                turno.getHorarioPausaAlmoco(),
                turno.getHorarioRetornoAlmoco(),
                turno.getHorarioFimJornada()
        );

        if(turno.getId() == null){
            return escalaProcura.isPresent();
        }

        return escalaProcura.map(
                e -> e.getId().equals(turno.getId())
        ).orElse(false);
    }
}
