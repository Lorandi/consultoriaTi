package com.consultoriaTi.gestao.dto;

import com.consultoriaTi.gestao.enums.ProfessionalStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.math.BigDecimal;

public record ProfessionalCreateDTO(String name,
                                    String corporateEmail,
                                    @JsonIgnore
                                    ProfessionalStatusEnum professionalStatus,
                                    String phone,
                                    BigDecimal remuneration,
                                    AddressCreateDTO address) {
    @Builder
    public ProfessionalCreateDTO {
    }
}



