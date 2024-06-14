package com.consultoriaTi.gestao.service;

import com.consultoriaTi.gestao.dto.ClientDTO;
import com.consultoriaTi.gestao.entity.Client;
import com.consultoriaTi.gestao.event.consumer.ClientPayload;
import com.consultoriaTi.gestao.helper.MessageHelper;
import com.consultoriaTi.gestao.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.consultoriaTi.gestao.exception.ErrorCodeEnum.ERROR_CLIENT_NOT_FOUND;
import static com.consultoriaTi.gestao.util.mapper.MapperConstants.clientMapper;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final MessageHelper messageHelper;

    public void create(final ClientPayload clientPayload) {
        Client client = repository.findByClientId(clientPayload.getClientId()).orElse(new Client().withClientId(clientPayload.getClientId()));
        clientMapper.updateClient(client, clientPayload);
        repository.save(client);
    }

    public Client findByClientId(final Long clientId) {
        return repository.findByClientId(clientId).orElseThrow(() -> {
            log.error(messageHelper.get(ERROR_CLIENT_NOT_FOUND, clientId.toString()));
            return new ResponseStatusException(NOT_FOUND, messageHelper.get(ERROR_CLIENT_NOT_FOUND, clientId.toString()));
        });
    }

    public ClientDTO findDTOByClientId(Long id) {
        return clientMapper.buildClientDTO(findByClientId(id));
    }

}
