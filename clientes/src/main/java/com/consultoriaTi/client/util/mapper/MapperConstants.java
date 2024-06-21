package com.consultoriaTi.client.util.mapper;

import org.mapstruct.factory.Mappers;

public class MapperConstants {

    private MapperConstants() {
    }

    public static final AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);
    public static final ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);
}
