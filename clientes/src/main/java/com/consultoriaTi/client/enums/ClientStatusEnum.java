package com.consultoriaTi.client.enums;


import com.consultoriaTi.client.enums.serializer.EnumSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonSerialize(using = EnumSerializer.class)
public enum ClientStatusEnum implements EnumDescription {


    ACTIVE("Ativa"),
    NOT_ACTIVE("Não Ativa"),;

    private final String description;
}
