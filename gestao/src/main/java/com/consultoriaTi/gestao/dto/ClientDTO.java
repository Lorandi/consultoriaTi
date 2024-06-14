package com.consultoriaTi.gestao.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@With
@Value
@Builder
@Jacksonized
public class ClientDTO {

    Long clientId;
    String name;
}
