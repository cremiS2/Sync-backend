package com.projeto.tcc.dto.exit;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;

public record StockResultDTO(
        String codigo,
        String nome,
        String categoria,
        Integer quantidade,
        String unidade,
        BigDecimal precoUnitario,
        String fornecedor,
        Date dataEntrada,
        Date dataValidade,
        String localizacao,
        String status,
        String descricao
) {
}
