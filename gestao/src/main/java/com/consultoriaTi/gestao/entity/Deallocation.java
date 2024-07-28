package com.consultoriaTi.gestao.entity;


import com.consultoriaTi.gestao.enums.DeallocationReasonEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@With
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deallocation")
public class Deallocation {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long allocationId;
    private LocalDate deallocationDate;
    @Enumerated(STRING)
    private DeallocationReasonEnum deallocationReason;
}
