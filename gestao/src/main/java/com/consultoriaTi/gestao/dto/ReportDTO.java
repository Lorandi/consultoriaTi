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
public class ReportDTO {

    Long totalProfessionals;
    Long professionalsAllocated;
    Long professionalsNotAllocated;
    Long totalActiveAllocations;
    Long totalScheduledAllocations;
    BigDecimal revenue;
    BigDecimal expenses;
    BigDecimal profit;
}
