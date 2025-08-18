package com.projeto.tcc.repository;

import com.projeto.tcc.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface EscalaFuncionarioRepository extends JpaRepository<Turno, Long> {

    Optional<Turno> findByTurnoFuncionarioAndHorarioInicioJornadaAndHorarioPausaAlmocoAndHorarioRetornoAlmocoAndHorarioFimJornada(
            com.projeto.tcc.enuns.Turno turno,
            LocalTime horarioInicioJornada,
            LocalTime horarioPausaAlmoco,
            LocalTime horarioRetornoAlmoco,
            LocalTime horarioFimJornada
    );
}
