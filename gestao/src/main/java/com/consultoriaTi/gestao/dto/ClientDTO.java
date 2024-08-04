package com.consultoriaTi.gestao.dto;

import com.consultoriaTi.gestao.enums.ClientStatusEnum;
import lombok.With;

@With
public record ClientDTO(Long clientId,
                        String name,
                        String phone,
                        ClientStatusEnum clientStatus,
                        String contactEmail,
                        AddressDTO address) {}



