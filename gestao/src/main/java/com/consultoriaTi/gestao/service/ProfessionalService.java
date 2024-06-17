package com.consultoriaTi.gestao.service;

import com.consultoriaTi.gestao.dto.AddressDTO;
import com.consultoriaTi.gestao.dto.ProfessionalCreateDTO;
import com.consultoriaTi.gestao.dto.ProfessionalDTO;
import com.consultoriaTi.gestao.dto.ProfessionalUpdateDTO;
import com.consultoriaTi.gestao.entity.Professional;
import com.consultoriaTi.gestao.enums.ProfessionalStatusEnum;
import com.consultoriaTi.gestao.helper.JsonUtils;
import com.consultoriaTi.gestao.helper.MessageHelper;
import com.consultoriaTi.gestao.repository.ProfessionalRepository;
import com.consultoriaTi.gestao.repository.spec.ProfessionalSpecification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.consultoriaTi.gestao.exception.ErrorCodeEnum.ERROR_PROFESSIONAL_NOT_FOUND;
import static com.consultoriaTi.gestao.util.mapper.MapperConstants.addressMapper;
import static com.consultoriaTi.gestao.util.mapper.MapperConstants.professionalMapper;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfessionalService {

    private final ProfessionalRepository repository;
    private final MessageHelper messageHelper;
    private final AddressService addressService;

    public ProfessionalDTO create(final ProfessionalCreateDTO requestDTO) {
        AddressDTO addressDTO = null;
        if (requestDTO.address() != null) {
            addressDTO = addressService.create(requestDTO.address());
        }

        Professional professional = repository.save(professionalMapper.buildEntity(requestDTO)
                .withProfessionalStatus(ProfessionalStatusEnum.NOT_ALLOCATED)
                .withAddressId(addressDTO != null ? addressDTO.id() : null));

        JsonUtils.logObject(log,"Professional created", professional);

        return professionalMapper.buildDTO(professional).withAddress(addressDTO);
    }

    public ProfessionalDTO update(final ProfessionalUpdateDTO updateDTO) {
        Professional professional = findById(updateDTO.id());

        AddressDTO addressDTO = null;

        if (professional.getAddressId() == null && updateDTO.address() != null) {
            addressDTO = addressService.create(updateDTO.address());
        } else if (updateDTO.address() != null) {
            addressDTO = addressService.update(addressMapper.buildAddressUpdateDTO(updateDTO.address())
                    .withId(professional.getAddressId()));
        }

        ProfessionalDTO professionalDTO = professionalMapper.buildDTO(repository.save(professional.withName(updateDTO.name())
                .withCorporateEmail(updateDTO.corporateEmail())
                .withPhone(updateDTO.phone())
                .withRemuneration(updateDTO.remuneration())
                .withAddressId(addressDTO != null ? addressDTO.id() : null)
        )).withAddress(addressDTO);

        JsonUtils.logObject(log,"Professional updated",  professionalDTO);

        return professionalDTO;
    }

    public Professional findById(final Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.error(messageHelper.get(ERROR_PROFESSIONAL_NOT_FOUND, id.toString()));
            return new ResponseStatusException(NOT_FOUND, messageHelper.get(ERROR_PROFESSIONAL_NOT_FOUND, id.toString()));
        });
    }

    public ProfessionalDTO findDTOById(final Long id) {
        Professional professional = findById(id);

        AddressDTO addressDTO = Optional.ofNullable(professional.getAddressId())
                .map(addressService::findDTOById)
                .orElse(null);

        return professionalMapper.buildDTO(professional).withAddress(addressDTO);

    }

    public void delete(final Long id) {
        Professional professional = findById(id);
        repository.delete(professional);
        JsonUtils.logObject(log,"Professional deleted:", professional);
    }

    public List<ProfessionalDTO> findAll(final Optional<String> name,
                                         Optional<String> corporateEmail,
                                         Optional<List<ProfessionalStatusEnum>> professionalStatus,
                                         Optional<String> phone) {
        return repository.findAll(ProfessionalSpecification.builder().name(name).corporateEmail(corporateEmail)
                        .professionalStatus(professionalStatus).phone(phone).build()).stream()
                .map(professional -> {
                    AddressDTO addressDTO = Optional.ofNullable(professional.getAddressId())
                            .map(addressService::findDTOById)
                            .orElse(null);
                    return professionalMapper.buildDTO(professional).withAddress(addressDTO);
                })
                .sorted(Comparator.comparing(ProfessionalDTO::id))
                .collect(Collectors.toList());
    }

    public void save(final Professional professional) {
        repository.save(professional);
    }
}