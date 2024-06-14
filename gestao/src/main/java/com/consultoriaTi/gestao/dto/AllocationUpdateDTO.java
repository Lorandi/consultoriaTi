package com.consultoriaTi.gestao.dto;

import com.consultoriaTi.gestao.enums.AllocationStatusEnum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class AllocationUpdateDTO {

    @NotNull @Positive
    Long id;
    String role;
    @Positive
    BigDecimal valuePerHour;
    @NotNull
    LocalDate allocationStartDate;
    LocalDate allocationEndDate;
}
