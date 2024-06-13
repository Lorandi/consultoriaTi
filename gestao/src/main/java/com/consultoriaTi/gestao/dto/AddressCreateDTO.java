package com.consultoriaTi.gestao.dto;

import com.consultoriaTi.gestao.enums.ProfessionalStatusEnum;
import lombok.Builder;

import java.math.BigDecimal;

public record AddressCreateDTO(String country,
                               String state,
                               String city,
                               String street,
                               String number,
                               String complement,
                               String zipcode) {
    @Builder
    public AddressCreateDTO {
    }

    ;
}



