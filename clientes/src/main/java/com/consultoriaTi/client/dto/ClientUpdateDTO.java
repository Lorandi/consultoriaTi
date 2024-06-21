package com.consultoriaTi.client.dto;

import com.consultoriaTi.client.enums.ClientStatusEnum;
import lombok.Builder;

public record ClientUpdateDTO(Long clientId,
                              String name,
                              String phone,
                              ClientStatusEnum clientStatus,
                              String contactEmail,
                              AddressCreateDTO address) {
    @Builder
    public ClientUpdateDTO {};
}



