package br.com.fiap.api_patient.infrastructure.adapter.internal;

import br.com.fiap.api_patient.core.domain.Cpf;
import br.com.fiap.api_patient.core.domain.Patient;
import br.com.fiap.api_patient.core.port.out.PatientPortOut;
import br.com.fiap.api_patient.infrastructure.entity.CpfEntity;
import br.com.fiap.api_patient.infrastructure.entity.PatientEntity;
import br.com.fiap.api_patient.infrastructure.mapper.CpfDomainEntityMapper;
import br.com.fiap.api_patient.infrastructure.mapper.PatientDomainEntityMapper;
import br.com.fiap.api_patient.infrastructure.repository.PatientRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class PatientAdapterImpl implements PatientPortOut {

    private final PatientRepository patientRepository;

    public PatientAdapterImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    @Transactional
    public Patient createPatient(Patient patient) {
        PatientEntity patientEntity = PatientDomainEntityMapper.toPatientEntity(patient);
        patientEntity.getAddress().setPatient(patientEntity);
        return PatientDomainEntityMapper.createPatientResponse(patientRepository.save(patientEntity));
    }

    @Override
    public Optional<Patient> getPatientById(Long patientId) {
        return patientRepository.findById(patientId)
                .map(PatientDomainEntityMapper::toPatient);
    }

    @Override
    public Optional<Patient> getPatientByEmail(String email) {
        return patientRepository.findByEmail(email)
                .map(PatientDomainEntityMapper::toPatient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return PatientDomainEntityMapper.allPatients(patientRepository.findAll());
    }

    @Override
    @Transactional
    public Patient updatePatient(Patient outdatedPatient) {
        PatientEntity outdatedPatientEntity = PatientDomainEntityMapper.toPatientEntity(outdatedPatient);
        outdatedPatientEntity.getAddress().setPatient(outdatedPatientEntity);
        return PatientDomainEntityMapper.toPatient(patientRepository.save(outdatedPatientEntity));
    }

    @Override
    @Transactional
    public void deletePatientById(Long patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    public boolean existsPatientById(Long patientId) {
        return patientRepository.existsById(patientId);
    }

    @Override
    public boolean existsPatientByEmail(String email) {
        return patientRepository.existsByEmail(email);
    }

    @Override
    public boolean existsPatientByCpf(Cpf cpf) {
        CpfEntity cpfEntity = CpfDomainEntityMapper.toCpfEntity(cpf);
        return patientRepository.existsByCpf(cpfEntity);
    }
}
