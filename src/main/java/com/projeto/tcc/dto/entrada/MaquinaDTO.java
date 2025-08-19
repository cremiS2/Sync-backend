package com.projeto.tcc.dto.entrada;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

public record MaquinaDTO(

        @NotBlank(message = "Por favor, preencha o campo")
        @Size(max = 200, message = "Campo excede o tamanho máximo de 200 caracteres")
        String name,

        @NotNull(message = "Por favor, preencha o campo")
        Long sector,

        @NotNull(message = "Por favor, preencha o campo. Tipos: OPERANDO, PARADA, EM_MANUTENCAO, AVARIADA")
        String status,

        Float oee,

        Set<Integer> employees,

        @NotNull(message = "Por favor, preencha o campo")
        Integer throughput,

        LocalDate lastMaintenance,

        @NotBlank(message = "Por favor, preencha o campo")
        String photo,

        @NotNull(message = "Por favor, preencha o campo com o número de série da máquina")
        @Max(value = 99999, message = "É necessário enviar exatamente 5 números nesse campo")
        @Min(value = 10000, message = "É necessário enviar exatamente 5 números nesse campo")
        Integer serieNumber,

        @NotNull(message = "Por favor, preencha o campo")
        Long modelMachine

){}
