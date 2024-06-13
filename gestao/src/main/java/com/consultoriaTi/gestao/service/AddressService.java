package com.consultoriaTi.gestao.service;


import com.consultoriaTi.gestao.dto.AddressCreateDTO;
import com.consultoriaTi.gestao.dto.AddressDTO;
import com.consultoriaTi.gestao.dto.AddressUpdateDTO;
import com.consultoriaTi.gestao.dto.ProfessionalCreateDTO;
import com.consultoriaTi.gestao.entity.Address;
import com.consultoriaTi.gestao.helper.MessageHelper;
import com.consultoriaTi.gestao.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.consultoriaTi.gestao.exception.ErrorCodeEnum.ERROR_ADDRESS_NOT_FOUND;
import static com.consultoriaTi.gestao.util.mapper.MapperConstants.addressMapper;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.apache.logging.log4j.util.Strings.isNotBlank;
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
