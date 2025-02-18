package br.com.fiap.api_patient.infrastructure.mapper;

import br.com.fiap.api_patient.core.domain.Patient;
import br.com.fiap.api_patient.infrastructure.entity.PatientEntity;

import java.util.ArrayList;
import java.util.List;

public class PatientDomainEntityMapper {

    private PatientDomainEntityMapper() {
    }

    public static PatientEntity toPatientEntity(Patient patient) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patient.getId());
        patientEntity.setFullName(patient.getFullName());
        patientEntity.setEmail(patient.getEmail());
        patientEntity.setMobilePhoneNumber(patient.getMobilePhoneNumber());
        patientEntity.setCpf(CpfDomainEntityMapper.toCpfEntity(patient.getCpf()));
        patientEntity.setAddress(AddressDomainEntityMapper.toAddressEntity(patient.getAddress()));
        return patientEntity;
    }

    public static Patient toPatient(PatientEntity patientEntity) {
        Patient patient = new Patient();
        patient.setId(patientEntity.getId());
        patient.setFullName(patientEntity.getFullName());
        patient.setEmail(patientEntity.getEmail());
        patient.setMobilePhoneNumber(patientEntity.getMobilePhoneNumber());
        patient.setCpf(CpfDomainEntityMapper.toCpf(patientEntity.getCpf()));
        patient.setAddress(AddressDomainEntityMapper.toAddress(patientEntity.getAddress()));
        return patient;
    }

    public static Patient createPatientResponse(PatientEntity patientEntity) {
        Patient patient = new Patient();
        patient.setId(patientEntity.getId());
        return patient;
    }

    public static List<Patient> allPatients(List<PatientEntity> patientEntityList) {
        List<Patient> patientList = new ArrayList<>();
        patientEntityList.forEach(patientEntity -> {
            Patient patient = toPatient(patientEntity);
            patientList.add(patient);
        });
        return patientList;
    }
}
