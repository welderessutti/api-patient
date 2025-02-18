package br.com.fiap.api_patient.framework.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllPatientsResponseDto {

    List<PatientResponseDto> patients;
}
