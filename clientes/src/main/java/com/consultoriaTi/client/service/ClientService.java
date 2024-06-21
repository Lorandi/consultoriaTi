package com.consultoriaTi.client.service;

import com.consultoriaTi.client.dto.AddressDTO;
import com.consultoriaTi.client.dto.ClientCreateDTO;
import com.consultoriaTi.client.dto.ClientDTO;
import com.consultoriaTi.client.dto.ClientUpdateDTO;
import com.consultoriaTi.client.entity.Client;
import com.consultoriaTi.client.enums.ClientStatusEnum;
import com.consultoriaTi.client.helper.JsonUtils;
import com.consultoriaTi.client.helper.MessageHelper;
import com.consultoriaTi.client.repository.ClientRepository;

import com.consultoriaTi.client.repository.spec.ClientSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.consultoriaTi.client.exception.ErrorCodeEnum.ERROR_CLIENT_NOT_FOUND;
import static com.consultoriaTi.client.util.mapper.MapperConstants.addressMapper;
import static com.consultoriaTi.client.util.mapper.MapperConstants.clientMapper;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final MessageHelper messageHelper;
    private final AddressService addressService;

    public ClientDTO create(final ClientCreateDTO requestDTO) {
        AddressDTO addressDTO = null;
        if (requestDTO.address() != null) {
            addressDTO = addressService.create(requestDTO.address());
        }

        Client client = repository.save(clientMapper.buildEntity(requestDTO)
                .withAddressId(addressDTO != null ? addressDTO.id() : null));

        JsonUtils.logObject(log, "client created", client);

        return clientMapper.buildDTO(client).withAddress(addressDTO);
    }

    public ClientDTO update(final ClientUpdateDTO updateDTO) {
        Client client = findById(updateDTO.clientId());

        AddressDTO addressDTO = null;

        if (client.getAddressId() == null && updateDTO.address() != null) {
            addressDTO = addressService.create(updateDTO.address());
        } else if (updateDTO.address() != null) {
            addressDTO = addressService.update(addressMapper.buildAddressUpdateDTO(updateDTO.address())
                    .withId(client.getAddressId()));
        }

        ClientDTO clientDTO = clientMapper.buildDTO(repository.save(client
                .withName(updateDTO.name())
                .withPhone(updateDTO.phone())
                .withClientStatus(updateDTO.clientStatus())
                .withContactEmail(updateDTO.contactEmail())
                .withAddressId(addressDTO != null ? addressDTO.id() : null)
        )).withAddress(addressDTO);

        JsonUtils.logObject(log, "Client updated", clientDTO);

        return clientDTO;
    }

    public Client findById(final Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.error(messageHelper.get(ERROR_CLIENT_NOT_FOUND, id.toString()));
            return new ResponseStatusException(NOT_FOUND, messageHelper.get(ERROR_CLIENT_NOT_FOUND, id.toString()));
        });
    }

    public ClientDTO findDTOById(final Long id) {
        Client client = findById(id);

        AddressDTO addressDTO = Optional.ofNullable(client.getAddressId())
                .map(addressService::findDTOById)
                .orElse(null);

        return clientMapper.buildDTO(client).withAddress(addressDTO);

    }

    public void delete(final Long id) {
        Client client = findById(id);
        repository.delete(client);
        JsonUtils.logObject(log, "client deleted:", client);
    }


    public Page<ClientDTO> findAll(final Optional<String> name,
                                   Optional<String> phone,
                                   Optional<String> contactEmail,
                                   Optional<List<ClientStatusEnum>> clientStatus,
                                   Pageable pageable) {
        return repository.findAll(ClientSpecification.builder().name(name).phone(phone).contactEmail(contactEmail).clientStatus(clientStatus).build(), pageable)
                .map(client -> {
                    AddressDTO addressDTO = Optional.ofNullable(client.getAddressId())
                            .map(addressService::findDTOById)
                            .orElse(null);
                    return clientMapper.buildDTO(client).withAddress(addressDTO);
                });
    }

}