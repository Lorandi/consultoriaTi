package com.consultoriaTi.gestao.repository;


import com.consultoriaTi.gestao.entity.Professional;
import com.consultoriaTi.gestao.enums.ProfessionalStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long>, JpaSpecificationExecutor<Professional> {

    Long countByProfessionalStatus(ProfessionalStatusEnum professionalStatus);

    @Query(value = "select sum(p.remuneration) from Professional p")
    BigDecimal calculateExpenses();
}
