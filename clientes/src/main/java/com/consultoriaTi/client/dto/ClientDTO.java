package com.consultoriaTi.client.dto;

import com.consultoriaTi.client.enums.ClientStatusEnum;
import lombok.With;

@With
public record ClientDTO(Long clientId,
                        String name,
                        String phone,
                        ClientStatusEnum clientStatus,
                        String contactEmail,
                        AddressDTO address) {}



