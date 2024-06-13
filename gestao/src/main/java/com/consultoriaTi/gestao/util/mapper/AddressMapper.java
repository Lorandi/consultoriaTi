package com.consultoriaTi.gestao.util.mapper;

import com.consultoriaTi.gestao.dto.AddressCreateDTO;
import com.consultoriaTi.gestao.dto.AddressDTO;
import com.consultoriaTi.gestao.dto.AddressUpdateDTO;
import com.consultoriaTi.gestao.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AddressMapper {


    AddressDTO buildAddressDTO(Address address);

    @Mapping(target = "id", ignore = true)
    Address buildAddress(AddressCreateDTO address);

    @Mapping(target = "id", source = "id")
    Address buildAddress(AddressUpdateDTO address);

    @Mapping(target = "id", ignore = true)
    AddressUpdateDTO buildAddressUpdateDTO(AddressCreateDTO address);
}
