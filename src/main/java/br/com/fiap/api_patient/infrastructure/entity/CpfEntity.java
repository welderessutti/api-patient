package br.com.fiap.api_patient.infrastructure.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CpfEntity {

    @Column(name = "document_number")
    private String documentNumber;
}
