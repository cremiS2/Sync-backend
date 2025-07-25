package com.projeto.tcc.dto.entrada;

import com.projeto.tcc.enuns.Turno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record EscalaFuncionarioDTO(

        @NotNull(message = "O campo não pode estar vazio. Possíveis entradas: DIURNO, NOTURNO")
        Turno turnoFuncionario,

        @NotNull(message = "O campo não pode estar vazio. Formato de entrada: 'HH:mm'")
        LocalTime horarioInicioJornada,

        @NotNull(message = "O campo não pode estar vazio. Formato de entrada: 'HH:mm'")
        LocalTime horarioPausaAlmoco,

        @NotNull(message = "O campo não pode estar vazio. Formato de entrada: 'HH:mm'")
        LocalTime horarioRetornoAlmoco,

        @NotNull(message = "O campo não pode estar vazio. Formato de entrada: 'HH:mm'")
        LocalTime horarioFimJornada
) {
}
