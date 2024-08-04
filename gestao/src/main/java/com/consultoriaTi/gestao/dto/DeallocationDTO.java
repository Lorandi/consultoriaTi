package com.consultoriaTi.gestao.dto;

import com.consultoriaTi.gestao.enums.AllocationStatusEnum;
import com.consultoriaTi.gestao.enums.DeallocationReasonEnum;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;

@With
@Value
@Builder
@Jacksonized
public class DeallocationDTO {

    Long id;
    Long allocationId;
    LocalDate deallocationDate;
    @Enumerated(STRING)
    DeallocationReasonEnum deallocationReason;
}
