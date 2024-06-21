package com.consultoriaTi.client.resource.handler;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

@Value
@With
@Jacksonized
@Builder
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    @Builder.Default
    LocalDateTime timestamp = now();
    String error;
    Integer status;
    List<String> message;
    String path;
    String trace;

}