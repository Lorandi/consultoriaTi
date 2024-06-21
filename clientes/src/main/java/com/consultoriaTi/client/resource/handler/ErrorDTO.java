package com.consultoriaTi.client.resource.handler;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Value
@With
@Jacksonized
@Builder
public class ErrorDTO {
    @Builder.Default
    LocalDateTime timestamp = now();
    int status;
    String error;
    String message;
    String path;
}
