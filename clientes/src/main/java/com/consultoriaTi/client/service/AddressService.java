package com.consultoriaTi.client.service;

import com.consultoriaTi.client.dto.AddressCreateDTO;
import com.consultoriaTi.client.dto.AddressDTO;
import com.consultoriaTi.client.dto.AddressUpdateDTO;
import com.consultoriaTi.client.entity.Address;
import com.consultoriaTi.client.helper.MessageHelper;
import com.consultoriaTi.client.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import static com.consultoriaTi.client.exception.ErrorCodeEnum.ERROR_ADDRESS_NOT_FOUND;
import static com.consultoriaTi.client.util.mapper.MapperConstants.addressMapper;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;
    private final MessageHelper messageHelper;

    public AddressDTO create(AddressCreateDTO request) {
        final var addressSaved = repository.save(addressMapper.buildAddress(request));
        return findDTOById(addressSaved.getId());
    }

    public AddressDTO update(AddressUpdateDTO addressUpdate) {
        findById(addressUpdate.id());

        return addressMapper.buildAddressDTO(repository.save(
                addressMapper.buildAddress(addressUpdate)));
    }

    public AddressDTO findDTOById(Long id) {
        return addressMapper.buildAddressDTO(findById(id));
    }

    private Address findById(final Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.error(messageHelper.get(ERROR_ADDRESS_NOT_FOUND, id.toString()));
            return new ResponseStatusException(NOT_FOUND, messageHelper.get(ERROR_ADDRESS_NOT_FOUND, id.toString()));
        });
    }

    public void delete(Long id) {
        findById(id);
        repository.delete(findById(id));
    }

}
