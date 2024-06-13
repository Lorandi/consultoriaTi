package com.consultoriaTi.gestao.dto;

import lombok.Builder;
import lombok.With;

@With
public record AddressUpdateDTO(Long id,
                               String country,
                               String state,
                               String city,
                               String street,
                               String number,
                               String complement,
                               String zipcode) {
    @Builder
    public AddressUpdateDTO {}
}



