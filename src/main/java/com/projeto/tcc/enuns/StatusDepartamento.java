package com.projeto.tcc.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusDepartamento {
    ATIVO("ativo"),
    INATIVO("inativo");

    private String name;


}
