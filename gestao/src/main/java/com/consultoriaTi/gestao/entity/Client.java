package com.consultoriaTi.gestao.entity;

import com.consultoriaTi.gestao.enums.ClientStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private String name;
    private String phone;
    private Long addressId;
    @Enumerated(STRING)
    private ClientStatusEnum clientStatus;
    String contactEmail;
}
