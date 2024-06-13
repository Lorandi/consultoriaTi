package com.consultoriaTi.gestao.repository;


import com.consultoriaTi.gestao.entity.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long>, JpaSpecificationExecutor<Professional> {
    //    @Query(value = "SELECT * FROM survey  WHERE status = 'OPEN' AND end_time < CURRENT_TIMESTAMP", nativeQuery = true)
    //    List<Survey>  findAllSurveysToUpdateSurveyStatusToClosed();
}
