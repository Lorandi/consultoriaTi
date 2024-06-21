package com.consultoriaTi.client.util.mapper;

import com.consultoriaTi.client.dto.AddressCreateDTO;
import com.consultoriaTi.client.dto.AddressDTO;
import com.consultoriaTi.client.dto.AddressUpdateDTO;
import com.consultoriaTi.client.entity.Address;
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
