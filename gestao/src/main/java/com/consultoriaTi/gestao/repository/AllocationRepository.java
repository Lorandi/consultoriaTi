package com.consultoriaTi.gestao.repository;

import com.consultoriaTi.gestao.entity.Allocation;
import com.consultoriaTi.gestao.enums.AllocationStatusEnum;
import feign.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long>, JpaSpecificationExecutor<Allocation> {

    @Query(value = "select a from Allocation a where a.professionalId = ?1 and a.clientId = ?2 and a.allocationStatus in ('ACTIVE', 'SCHEDULED')")
    List<Allocation> findByProfessionalIdAndClientIdAndAllocationStatusIsActiveOrScheduled(Long professionalId, Long clientId);

    @Query(value = "select a from Allocation a where date(a.allocationStartDate) <= current_date and a.allocationStatus = 'SCHEDULED' ")
    List<Allocation> findAllAllocationsToUpdateAllocationStatusTodayToActive();

    @Query(value = "select a from Allocation a where date(a.allocationEndDate) <= current_date and a.allocationStatus = 'ACTIVE' ")
    List<Allocation> findAllAllocationsToUpdateAllocationStatusTodayToFinished();

    @Query(value = "select a from Allocation a where a.professionalId = ?1  and a.allocationStatus = 'ACTIVE' ")
    List<Allocation> findAllAllocationWithStatusActiveForThisProfessional(final Long professionalId);

    Long countByAllocationStatus(AllocationStatusEnum allocationStatus);

    @Query(value = "select sum(a.valuePerHour)*176 from Allocation a where a.allocationStatus = 'ACTIVE'")
    BigDecimal calculateRevenue();
}
