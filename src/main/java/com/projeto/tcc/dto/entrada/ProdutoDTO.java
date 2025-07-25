package com.projeto.tcc.dto.entrada;

import com.projeto.tcc.enuns.Turno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record ProdutoDTO(

        @NotBlank(message = "Por favor, preencha o campo")
        @Size(max = 5, message = "Campo excede o tamanho máximo de 5 caracteres")
        String codigoProduto,

        @Size(max = 200, message = "Campo excede o tamanho máximo de 200 caracteres")
        @NotBlank(message = "Por favor, preencha o campo")
        String nomeProduto,

        @NotBlank(message = "Por favor, preencha o campo")
        LocalDate validade,

        @NotBlank(message = "O campo não pode estar vazio. Possíveis entradas: DIURNO, NOTURNO")
        Turno turnoProducao,

        @NotNull(message = "Por favor, preencha o campo")
        @NotEmpty(message = "Por favor, coloque no mínimo um id na lista")
        List<Long> maquinasId
) {
}
