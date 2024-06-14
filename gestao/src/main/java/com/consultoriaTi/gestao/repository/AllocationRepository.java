package com.consultoriaTi.gestao.repository;

import com.consultoriaTi.gestao.entity.Allocation;
import feign.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long>, JpaSpecificationExecutor<Allocation> {

    Optional<Allocation> findByProfessionalId(Long professionalId);

    @Query(value = "select a from Allocation a where a.professionalId = ?1 and a.clientId = ?2 and a.allocationStatus in ('ACTIVE', 'SCHEDULED')")
    List<Allocation> findByProfessionalIdAndClientIdAndAllocationStatusIsActiveOrScheduled(Long professionalId, Long clientId);


    @Query(value = "select a from Allocation a where date(a.allocationStartDate) <= current_date and a.allocationStatus = 'SCHEDULED' ")
    List<Allocation> findAllAllocationsToUpdateAllocationStatusTodayToActive();

    @Query(value = "select a from Allocation a where date(a.allocationEndDate) <= current_date and a.allocationStatus = 'ACTIVE' ")
    List<Allocation> findAllAllocationsToUpdateAllocationStatusTodayToFinished();
}
