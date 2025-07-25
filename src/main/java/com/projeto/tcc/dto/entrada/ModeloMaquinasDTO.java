package com.projeto.tcc.dto.entrada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModeloMaquinasDTO
        (
        @NotBlank(message = "Não é possível enviar esse campo vazio")
        @Size(max = 200, message = "Campo excede o tamanho máximo de 200 caracteres")
        String nomeModelo,

        @NotBlank(message = "Não é possível enviar esse campo vazio")
        @Size(max = 300, message = "Campo excede o tamanho máximo de 200 caracteres")
        String descricaoModelo
) {
}
