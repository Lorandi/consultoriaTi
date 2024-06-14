package com.consultoriaTi.gestao.dto;

import com.consultoriaTi.gestao.enums.AllocationStatusEnum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@With
@Value
@Builder
@Jacksonized
public class AllocationCreateDTO {

    @NotNull @Positive
    Long professionalId;
    @NotNull @Positive
    Long clientId;
    String role;
    @Positive
    BigDecimal valuePerHour;
    @NotNull
    LocalDate allocationStartDate;
    LocalDate allocationEndDate;
}
