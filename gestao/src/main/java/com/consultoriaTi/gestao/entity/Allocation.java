package com.consultoriaTi.gestao.entity;


import com.consultoriaTi.gestao.enums.AllocationStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@With
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "allocation")
public class Allocation  {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long professionalId;
    private Long clientId;
    private String role;
    private BigDecimal valuePerHour;
    private LocalDate allocationStartDate;
    private LocalDate allocationEndDate;
    @Enumerated(STRING)
    private AllocationStatusEnum allocationStatus;
}
