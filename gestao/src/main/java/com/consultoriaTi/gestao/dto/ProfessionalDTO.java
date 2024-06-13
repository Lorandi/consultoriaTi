package com.consultoriaTi.gestao.dto;

import com.consultoriaTi.gestao.enums.ProfessionalStatusEnum;
import lombok.With;

import java.math.BigDecimal;

@With
public record ProfessionalDTO(Long id,
                              String name,
                              String corporateEmail,
                              ProfessionalStatusEnum professionalStatus,
                              String phone,
                              BigDecimal remuneration,
                              AddressDTO address) {}



