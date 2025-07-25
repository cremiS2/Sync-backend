package com.projeto.tcc.dto.entrada;

import com.projeto.tcc.enuns.StatusMaquina;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record MaquinaDTO(
        @NotBlank(message = "Por favor, preencha o campo")
        @Size(max = 200, message = "Campo excede o tamanho máximo de 200 caracteres")
        String nomeMaquina,

        @NotNull(message = "Por favor, preencha o campo com o número de série da máquina")
        @Max(value = 99999, message = "É necessário enviar exatamente 5 números nesse campo")
        @Min(value = 10000, message = "É necessário enviar exatamente 5 números nesse campo")
        Integer numeroSerie,

        @NotNull(message = "Por favor, preencha o campo")
        Long setor,

        @NotNull(message = "Por favor, preencha o campo")
        Long modeloMaquina,

        @NotNull(message = "Por favor, preencha o campo")
        Long localMaquina,


        LocalDate ultimaManutencao,

        @NotNull(message = "Por favor, preencha o campo. Tipos: OPERANDO, PARADA, EM_MANUTENCAO, AVARIADA")
        StatusMaquina status,

        Long funcionarioOperando
) {

}
