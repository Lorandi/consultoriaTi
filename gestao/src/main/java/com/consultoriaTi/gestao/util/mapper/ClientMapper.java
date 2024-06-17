package com.consultoriaTi.gestao.util.mapper;

import com.consultoriaTi.gestao.dto.ClientDTO;
import com.consultoriaTi.gestao.entity.Client;
import com.consultoriaTi.gestao.event.consumer.ClientPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface ClientMapper {

    void updateClient(@MappingTarget Client target, ClientPayload payload);

    @Mapping(target = "clientId", source = "clientId")
    ClientDTO buildClientDTO(Client client);

    List<ClientDTO> buildClientDTOList(List<Client> clients);
}
