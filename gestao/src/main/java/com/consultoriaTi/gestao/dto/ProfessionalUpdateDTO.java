package com.consultoriaTi.gestao.dto;

import com.consultoriaTi.gestao.enums.ProfessionalStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.math.BigDecimal;

public record ProfessionalUpdateDTO(Long id,
                                    String name,
                                    String corporateEmail,
                                    @JsonIgnore
                                    ProfessionalStatusEnum professionalStatus,
                                    String phone,
                                    BigDecimal remuneration,
                                    AddressCreateDTO address) {
    @Builder
    public ProfessionalUpdateDTO {};
}



