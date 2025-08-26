package com.projeto.tcc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusDepartment {
    ACTIVE("active"),
    INACTIVE("inactive");

    private String name;


}
