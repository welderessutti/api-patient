package br.com.fiap.api_patient.framework.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpfResponseDto {

    private String documentNumber;
}
