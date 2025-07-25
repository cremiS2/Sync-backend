package com.projeto.tcc.dto.entrada;

import com.projeto.tcc.enuns.StatusMaquina;

import java.time.LocalDate;

public record RelatorioMaquinaDTO(
     String nome,
     LocalDate ultima_manutencao,
     String setor,
     StatusMaquina status
){}
