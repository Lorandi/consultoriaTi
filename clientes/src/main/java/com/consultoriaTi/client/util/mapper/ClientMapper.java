package com.consultoriaTi.client.util.mapper;

import com.consultoriaTi.client.dto.ClientCreateDTO;
import com.consultoriaTi.client.dto.ClientDTO;
import com.consultoriaTi.client.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientMapper {


    @Mapping(target = "clientId", ignore = true)
    Client buildEntity(ClientCreateDTO requestDTO);

    ClientDTO buildDTO(Client client);

}
