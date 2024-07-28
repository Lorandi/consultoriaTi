package com.consultoriaTi.gestao.dto;

import com.consultoriaTi.gestao.enums.DeallocationReasonEnum;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;

@With
@Value
@Builder
@Jacksonized
public class DeallocationCreateDTO {

    @NonNull
    Long allocationId;
    @NonNull
    @Enumerated(STRING)
    DeallocationReasonEnum deallocationReason;
}
