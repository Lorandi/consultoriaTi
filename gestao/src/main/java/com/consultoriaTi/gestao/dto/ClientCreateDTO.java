package com.consultoriaTi.gestao.dto;


import com.consultoriaTi.gestao.enums.ClientStatusEnum;
import lombok.Builder;

public record ClientCreateDTO(String name,
                              String phone,
                              ClientStatusEnum clientStatus,
                              String contactEmail,
                              AddressCreateDTO address) {
    @Builder
    public ClientCreateDTO {
    }
}



