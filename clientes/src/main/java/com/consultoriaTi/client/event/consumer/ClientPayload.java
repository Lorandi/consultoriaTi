package com.consultoriaTi.client.event.consumer;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@With
@Value
@Builder
@Jacksonized
public class ClientPayload {

    Long clientId;
    String name;

}
