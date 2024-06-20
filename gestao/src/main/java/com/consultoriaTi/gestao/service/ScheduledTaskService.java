package com.consultoriaTi.gestao.service;

import com.consultoriaTi.gestao.entity.Allocation;
import com.consultoriaTi.gestao.entity.Professional;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.consultoriaTi.gestao.enums.AllocationStatusEnum.ACTIVE;
import static com.consultoriaTi.gestao.enums.AllocationStatusEnum.FINISHED;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
@Profile("!test")
public class ScheduledTaskService {
    private final AllocationService allocationService;
    private final ProfessionalService professionalService;

    @PostConstruct
    public void executeTaskOnStartup() {
        updateAllocationStatus();
    }

    @Scheduled(cron = "0 0 8 * * *", zone = "America/Sao_Paulo")
    public void updateAllocationStatus() {
        List<Allocation> listAllocations = allocationService.findAllAllocationsToUpdateAllocationStatusTodayToActive();
        listAllocations.forEach(allocation ->{
            allocationService.saveAllocation(allocation.withAllocationStatus(ACTIVE));
            Professional professional = professionalService.findById(allocation.getProfessionalId());
            allocationService.updateProfessionalStatus(professional, ACTIVE);
        });
        List<Allocation> listAllocationsFinished = allocationService.findAllAllocationsToUpdateAllocationStatusTodayToFinished();
        listAllocationsFinished.forEach(allocation -> {
            allocationService.saveAllocation(allocation.withAllocationStatus(FINISHED));
            Professional professional = professionalService.findById(allocation.getProfessionalId());
            allocationService.updateProfessionalStatusIfNoActiveAllocations(professional);
        });
    }
}
