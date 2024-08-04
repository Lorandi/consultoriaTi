package com.consultoriaTi.gestao.service;

import com.consultoriaTi.gestao.dto.AllocationCreateDTO;
import com.consultoriaTi.gestao.dto.AllocationDTO;
import com.consultoriaTi.gestao.dto.AllocationUpdateDTO;
import com.consultoriaTi.gestao.entity.Allocation;
import com.consultoriaTi.gestao.entity.Professional;
import com.consultoriaTi.gestao.enums.AllocationStatusEnum;
import com.consultoriaTi.gestao.helper.MessageHelper;
import com.consultoriaTi.gestao.repository.AllocationRepository;
import com.consultoriaTi.gestao.repository.spec.AllocationSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.consultoriaTi.gestao.enums.AllocationStatusEnum.*;
import static com.consultoriaTi.gestao.enums.ProfessionalStatusEnum.ALLOCATED;
import static com.consultoriaTi.gestao.enums.ProfessionalStatusEnum.NOT_ALLOCATED;
import static com.consultoriaTi.gestao.exception.ErrorCodeEnum.*;
import static com.consultoriaTi.gestao.util.mapper.MapperConstants.allocationMapper;
import static java.time.LocalDate.now;
import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class AllocationService {

    private final AllocationRepository repository;
    private final MessageHelper messageHelper;
    private final ProfessionalService professionalService;
    private final ClientService clientService;


    @Transactional
    public AllocationDTO create(final AllocationCreateDTO createDTO) {
        Professional professional = professionalService.findById(createDTO.getProfessionalId());
        clientService.findById(createDTO.getClientId());

        checkProfessionalAllocations(createDTO.getProfessionalId(), createDTO.getClientId());

        AllocationStatusEnum allocationStatus = checkAllocationStatusBasedOnDates(
                createDTO.getAllocationStartDate(), createDTO.getAllocationEndDate());

        AllocationDTO allocationDTO =  allocationMapper.buildAllocationDTO(repository.save(
                allocationMapper.buildAllocation(createDTO).withAllocationStatus(allocationStatus)));

        updateProfessionalStatus(professional, allocationStatus);

        return allocationDTO;
    }

    @Transactional
    public AllocationDTO update(final AllocationUpdateDTO updateDTO) {
        Allocation allocation = findById(updateDTO.getId());
        Professional professional = professionalService.findById(allocation.getProfessionalId());

        AllocationStatusEnum allocationStatus = checkAllocationStatusBasedOnDates(
                updateDTO.getAllocationStartDate(), updateDTO.getAllocationEndDate());

        AllocationDTO allocationDTO =  allocationMapper.buildAllocationDTO(repository.save(allocation
                .withRole(updateDTO.getRole())
                .withValuePerHour(updateDTO.getValuePerHour())
                .withAllocationStartDate(updateDTO.getAllocationStartDate())
                .withAllocationEndDate(updateDTO.getAllocationEndDate())
                .withAllocationStatus(allocationStatus)
        ));

        updateProfessionalStatus(professional, allocationStatus);

        return allocationDTO;
    }

    private void checkProfessionalAllocations(final Long professionalId, final Long clientId) {
        List<Allocation> allocationList = repository.findByProfessionalIdAndClientIdAndAllocationStatusIsActiveOrScheduled(
                professionalId, clientId);

        if (!allocationList.isEmpty()) {
            log.error(messageHelper.get(ERROR_PROFESSIONAL_ALREADY_ALLOCATED_ON_THIS_CLIENT, professionalId, clientId));
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_PROFESSIONAL_ALREADY_ALLOCATED_ON_THIS_CLIENT,
                    professionalId, clientId));
        }
    }

    private AllocationStatusEnum checkAllocationStatusBasedOnDates(LocalDate allocationStartDate, LocalDate allocationEndDate) {
        if (nonNull(allocationEndDate) && allocationEndDate.isBefore(allocationStartDate))
            throw new ResponseStatusException(BAD_REQUEST, messageHelper.get(ERROR_ALLOCATION_END_DATE_BEFORE_ALLOCATION_START_DATE));

        if(nonNull(allocationStartDate)){
            if (now().isBefore(allocationStartDate))
                return SCHEDULED;
            else if (now().isAfter(allocationStartDate) || now().isEqual(allocationStartDate))
                return ACTIVE;
        }

        return null;
    }

    public List<AllocationDTO> findAll(final AllocationSpecification allocationSpecification,
                                       final Pageable pageable) {
        return repository.findAll(allocationSpecification, pageable).stream().map(allocationMapper::buildAllocationDTO)
                .collect(Collectors.toList());
    }

    public Allocation findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        messageHelper.get(ERROR_ALLOCATION_NOT_FOUND, id)));
    }

    public AllocationDTO findDTOById(Long id) {
        return allocationMapper.buildAllocationDTO(findById(id));
    }

    public void updateProfessionalStatus(Professional professional, AllocationStatusEnum allocationStatus) {
        if (allocationStatus == null) return;

        if (allocationStatus.equals(ACTIVE)) {
            professional.setProfessionalStatus(ALLOCATED);
        } else if (allocationStatus.equals(FINISHED)) {
            updateProfessionalStatusIfNoActiveAllocations(professional);
        }

        professionalService.save(professional);
    }



    public void saveAllocation(Allocation allocation) {
        repository.save(allocation);
    }

    public List<Allocation> findAllAllocationsToUpdateAllocationStatusTodayToActive() {
        return repository.findAllAllocationsToUpdateAllocationStatusTodayToActive();
    }

    public List<Allocation> findAllAllocationsToUpdateAllocationStatusTodayToFinished() {
        return repository.findAllAllocationsToUpdateAllocationStatusTodayToFinished();
    }

    public void delete(Long id) {
        Allocation allocation = findById(id);
        repository.delete(allocation);
    }

    public void updateProfessionalStatusIfNoActiveAllocations(Professional professional){
        if (findAllAllocationsActiveForThisProfessional(professional.getId()).isEmpty()) {
            professional.setProfessionalStatus(NOT_ALLOCATED);
            professionalService.save(professional);
        }
    }

    public List<Allocation> findAllAllocationsActiveForThisProfessional(final Long professionalId) {
        return repository.findAllAllocationWithStatusActiveForThisProfessional(professionalId);
    }

    public Long countByAllocationStatus(AllocationStatusEnum allocationStatus) {
        return repository.countByAllocationStatus(allocationStatus);
    }

    public BigDecimal calculateRevenue() {
        return repository.calculateRevenue();
    }
}
