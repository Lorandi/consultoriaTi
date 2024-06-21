package com.consultoriaTi.client.dto;

import lombok.Builder;

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



