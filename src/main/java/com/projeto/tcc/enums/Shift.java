package com.projeto.tcc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Shift {
    DIURNO("diurno"),
    NOTURNO("noturno"),
    VESPERTINO("vespertino");

    private final String name;


}
