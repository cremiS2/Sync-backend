package com.projeto.tcc.dto.entry;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;

public record StockDTO(

        @NotBlank(message = "Por favor, preencha este campo")
        @Size(max = 5, message = "O código não pode ter mais de 5 caracteres ")
        String codigo,

        @NotBlank(message = "Por favor, preencha este campo")
        String nome,

        @NotBlank(message = "Por favor, preencha este campo")
        String categoria,

        @NotNull(message = "Por favor, preencha este campo")
        @Min(value = 0, message = "A quantidade não pode ser menor do que zero!")
        Integer quantidade,

        @NotBlank(message = "Por favor, preencha este campo")
        String unidade,

        @NotNull(message = "Por favor, preencha este campo")
        @Min(value = 0, message = "O preço não pode ser menor do que zero!")
        BigDecimal precoUnitario,

        @NotBlank(message = "Por favor, preencha este campo")
        String fornecedor,

        @PastOrPresent(message = "A data de entrada não pode ser no futuro")
        @NotNull(message = "Por favor, preencha este campo")
        Date dataEntrada,

        @FutureOrPresent(message = "A data de validade não pode já ter passado")
        @NotNull(message = "Por favor, preencha este campo")
        @NotNull(message = "Por favor, preencha este campo")
        Date dataValidade,

        @NotBlank(message = "Por favor, preencha este campo")
        String localizacao,

        @NotBlank(message = "Por favor, preencha este campo")
        String status,

        @NotBlank(message = "Por favor, preencha este campo")
        String descricao
) {
}
