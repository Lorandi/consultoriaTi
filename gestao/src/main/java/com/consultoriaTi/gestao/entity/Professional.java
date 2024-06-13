package com.consultoriaTi.gestao.entity;

import com.consultoriaTi.gestao.enums.ProfessionalStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "professional")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String corporateEmail;
    @Enumerated(STRING)
    private ProfessionalStatusEnum professionalStatus;
    private String phone;
    private Long addressId;
    private BigDecimal remuneration;
}
