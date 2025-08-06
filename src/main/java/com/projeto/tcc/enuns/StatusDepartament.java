package com.projeto.tcc.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusDepartament {
    ACTIVE("active"),
    INACTIVE("inactive");

    private String name;


}
