package br.com.fiap.api_patient.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;

    @Embedded
    private CpfEntity cpf;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "patient")
    private AddressEntity address;
}
