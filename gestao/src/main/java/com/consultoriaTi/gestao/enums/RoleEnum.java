package com.consultoriaTi.gestao.enums;

import com.consultoriaTi.gestao.enums.serializer.EnumSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonSerialize(using = EnumSerializer.class)
@AllArgsConstructor
@Getter
public enum RoleEnum implements EnumDescription {
    ADMIN("Administrador"),
    BUSINESS_PARTNER("Business Partner"),
    BASIC("Básico");

    private final String description;

    public String getRole() {
        return this.name();
    }
}
