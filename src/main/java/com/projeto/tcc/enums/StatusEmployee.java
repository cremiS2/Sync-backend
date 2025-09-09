package com.projeto.tcc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusEmployee {
    ACTIVE("active"),
    ON_LEAVE("on_leave"),
    MEDICAL_LEAVE("medical_leave");

    private final String name;
}
