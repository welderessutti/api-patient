package br.com.fiap.api_patient.core.port.in;

import br.com.fiap.api_patient.framework.dto.request.create.PatientCreateRequestDto;
import br.com.fiap.api_patient.framework.dto.request.update.PatientUpdateRequestDto;
import br.com.fiap.api_patient.framework.dto.response.AllPatientsResponseDto;
import br.com.fiap.api_patient.framework.dto.response.CreatePatientResponseDto;
import br.com.fiap.api_patient.framework.dto.response.PatientResponseDto;

public interface PatientPortIn {

    CreatePatientResponseDto createPatient(PatientCreateRequestDto request);

    PatientResponseDto getPatientById(Long patientId);

    PatientResponseDto getPatientByEmail(String email);

    AllPatientsResponseDto getAllPatients();

    PatientResponseDto updatePatient(Long patientId, PatientUpdateRequestDto request);

    void deletePatientById(Long patientId);
}
