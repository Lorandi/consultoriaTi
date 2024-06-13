package com.consultoriaTi.gestao.enums;

import com.consultoriaTi.gestao.enums.serializer.EnumSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonSerialize(using = EnumSerializer.class)
@AllArgsConstructor
@Getter
public enum ProfessionalStatusEnum implements EnumDescription {

    ALLOCATED("Alocadao"),
    NOT_ALLOCATED("Não alocado");

    private final String description;

}
