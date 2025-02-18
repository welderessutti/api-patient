package br.com.fiap.api_patient.framework.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDto {

    private Long id;
    private String fullName;
    private String email;
    private String mobilePhoneNumber;
    private CpfResponseDto cpf;
    private AddressResponseDto address;
}
