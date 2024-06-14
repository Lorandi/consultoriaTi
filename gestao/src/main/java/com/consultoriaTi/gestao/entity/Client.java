package com.consultoriaTi.gestao.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class Client {
    @Id
    private Long clientId;
    private String name;
}
