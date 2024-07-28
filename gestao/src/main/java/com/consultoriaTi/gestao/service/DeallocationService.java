package com.consultoriaTi.gestao.service;

import com.consultoriaTi.gestao.dto.DeallocationCreateDTO;
import com.consultoriaTi.gestao.dto.DeallocationDTO;
import com.consultoriaTi.gestao.entity.Allocation;
import com.consultoriaTi.gestao.entity.Deallocation;
import com.consultoriaTi.gestao.entity.Professional;
import com.consultoriaTi.gestao.enums.AllocationStatusEnum;
import com.consultoriaTi.gestao.helper.MessageHelper;
import com.consultoriaTi.gestao.repository.DeallocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.consultoriaTi.gestao.exception.ErrorCodeEnum.ERROR_ALLOCATION_STATUS_NOT_ACTIVE;
import static com.consultoriaTi.gestao.exception.ErrorCodeEnum.ERROR_DEALLOCATION_NOT_FOUND;
import static com.consultoriaTi.gestao.util.mapper.MapperConstants.deallocationMapper;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeallocationService {

    private final DeallocationRepository repository;
    private final MessageHelper messageHelper;
    private final ProfessionalService professionalService;
    private final AllocationService allocationService;


    @Transactional
    public DeallocationDTO create(final DeallocationCreateDTO createDTO) {
        Allocation allocation = allocationService.findById(createDTO.getAllocationId());

        checkIfAllocationStatusIsActive(allocation);

        Professional professional = professionalService.findById(allocation.getProfessionalId());

        DeallocationDTO deallocationDTO =  deallocationMapper.buildDeallocationDTO(repository.save(
                deallocationMapper.builDeallocation(createDTO).withDeallocationDate(LocalDate.now())));

        allocationService.saveAllocation(allocation
                .withAllocationStatus(AllocationStatusEnum.FINISHED)
                .withAllocationEndDate(LocalDate.now()));

        allocationService.updateProfessionalStatus(professional, AllocationStatusEnum.FINISHED);

        return deallocationDTO;
    }

    void checkIfAllocationStatusIsActive(Allocation allocation) {
        if (!allocation.getAllocationStatus().equals(AllocationStatusEnum.ACTIVE)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    messageHelper.get(ERROR_ALLOCATION_STATUS_NOT_ACTIVE, allocation.getId()));
        }
    }

    public List<DeallocationDTO> findAll(final Pageable pageable) {
        return repository.findAll(pageable).stream().map(deallocationMapper::buildDeallocationDTO)
                .collect(Collectors.toList());
    }

    public Deallocation findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        messageHelper.get(ERROR_DEALLOCATION_NOT_FOUND, id)));
    }

    public DeallocationDTO findDTOById(Long id) {
        return deallocationMapper.buildDeallocationDTO(findById(id));
    }

    public void delete(Long id) {
        Deallocation deallocation = findById(id);
        repository.delete(deallocation);
    }

}
