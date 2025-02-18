package br.com.fiap.api_patient.core.mapper;

import br.com.fiap.api_patient.core.domain.Patient;
import br.com.fiap.api_patient.framework.dto.request.create.PatientCreateRequestDto;
import br.com.fiap.api_patient.framework.dto.request.update.PatientUpdateRequestDto;
import br.com.fiap.api_patient.framework.dto.response.AllPatientsResponseDto;
import br.com.fiap.api_patient.framework.dto.response.PatientResponseDto;
import br.com.fiap.api_patient.framework.dto.response.CreatePatientResponseDto;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class PatientDtoDomainMapper {

    private PatientDtoDomainMapper() {
    }

    public static Patient toPatient(PatientCreateRequestDto request) {
        Patient patient = new Patient();
        patient.setFullName(request.getFullName());
        patient.setEmail(request.getEmail());
        patient.setMobilePhoneNumber(request.getMobilePhoneNumber());
        patient.setCpf(CpfDtoDomainMapper.toCpf(request.getCpf()));
        patient.setAddress(AddressDtoDomainMapper.toAddress(request.getAddress()));
        return patient;
    }

    public static PatientResponseDto toPatientResponseDto(Patient patient) {
        PatientResponseDto patientResponseDTO = new PatientResponseDto();
        patientResponseDTO.setId(patient.getId());
        patientResponseDTO.setFullName(patient.getFullName());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setMobilePhoneNumber(patient.getMobilePhoneNumber());
        patientResponseDTO.setCpf(CpfDtoDomainMapper.toCpfResponseDto(patient.getCpf()));
        patientResponseDTO.setAddress(AddressDtoDomainMapper.toAddressResponseDto(patient.getAddress()));
        return patientResponseDTO;
    }

    public static CreatePatientResponseDto toCreatePatientResponseDto(Patient patient) {
        CreatePatientResponseDto createPatientResponseDTO = new CreatePatientResponseDto();
        createPatientResponseDTO.setId(patient.getId());
        return createPatientResponseDTO;
    }

    public static AllPatientsResponseDto allPatientsResponseDto(List<Patient> patientList) {
        AllPatientsResponseDto allPatientsResponseDTO = new AllPatientsResponseDto();
        List<PatientResponseDto> patientResponseDtoList = new ArrayList<>();
        patientList.forEach(patient -> {
            PatientResponseDto patientResponseDTO = toPatientResponseDto(patient);
            patientResponseDtoList.add(patientResponseDTO);
        });
        allPatientsResponseDTO.setPatients(patientResponseDtoList);
        return allPatientsResponseDTO;
    }

    public static Patient updateRequestToPatient(PatientUpdateRequestDto request) {
        Patient patient = new Patient();
        if (nonNull(request.getFullName()))
            patient.setFullName(request.getFullName());
        if (nonNull(request.getEmail()))
            patient.setEmail(request.getEmail());
        if (nonNull(request.getMobilePhoneNumber()))
            patient.setMobilePhoneNumber(request.getMobilePhoneNumber());
        if (nonNull(request.getCpf()))
            patient.setCpf(CpfDtoDomainMapper.updateRequestToCpf(request.getCpf()));
        if (nonNull(request.getAddress())) {
            patient.setAddress(AddressDtoDomainMapper.updateRequestToAddress(request.getAddress()));
        }
        return patient;
    }
}
