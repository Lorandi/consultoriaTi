package com.consultoriaTi.client.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String state;
    private String city;
    private String street;
    private String number;
    private String complement;
    private String zipcode;
}
