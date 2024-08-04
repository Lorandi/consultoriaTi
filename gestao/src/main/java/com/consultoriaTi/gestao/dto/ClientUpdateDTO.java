package com.consultoriaTi.gestao.dto;

import com.consultoriaTi.gestao.enums.ClientStatusEnum;
import lombok.Builder;

public record ClientUpdateDTO(Long clientId,
                              String name,
                              String phone,
                              ClientStatusEnum clientStatus,
                              String contactEmail,
                              AddressCreateDTO address) {
    @Builder
    public ClientUpdateDTO {}
}



