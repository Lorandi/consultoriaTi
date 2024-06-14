package com.consultoriaTi.gestao.dto;

import com.consultoriaTi.gestao.enums.AllocationStatusEnum;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.LocalDate;

@With
@Value
@Builder
@Jacksonized
public class AllocationDTO {

    Long id;
    Long professionalId;
    Long clientId;
    String role;
    BigDecimal valuePerHour;
    LocalDate allocationStartDate;
    LocalDate allocationEndDate;
    AllocationStatusEnum allocationStatus;
}
