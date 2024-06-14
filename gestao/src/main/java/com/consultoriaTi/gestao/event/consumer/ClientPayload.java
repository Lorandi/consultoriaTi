package com.consultoriaTi.gestao.event.consumer;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@With
@Value
@Builder
@Jacksonized
public class ClientPayload {

    Long clientId;
    String name;

}
