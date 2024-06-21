package com.consultoriaTi.client.dto;

import lombok.Builder;

public record AddressDTO(Long id,
                         String country,
                         String state,
                         String city,
                         String street,
                         String number,
                         String complement,
                         String zipcode) {
    @Builder
    public AddressDTO {
    }
}



