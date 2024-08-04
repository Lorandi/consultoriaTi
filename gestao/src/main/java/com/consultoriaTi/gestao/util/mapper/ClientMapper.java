package com.consultoriaTi.gestao.util.mapper;


import com.consultoriaTi.gestao.dto.ClientCreateDTO;
import com.consultoriaTi.gestao.dto.ClientDTO;
import com.consultoriaTi.gestao.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientMapper {


    @Mapping(target = "clientId", ignore = true)
    Client buildEntity(ClientCreateDTO requestDTO);

    ClientDTO buildDTO(Client client);

}
