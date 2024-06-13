package com.consultoriaTi.gestao.util.mapper;

import org.mapstruct.factory.Mappers;

public class MapperConstants {

    private MapperConstants() {
    }

    public static final ProfessionalMapper professionalMapper = Mappers.getMapper(ProfessionalMapper.class);
    public static final AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);
}
