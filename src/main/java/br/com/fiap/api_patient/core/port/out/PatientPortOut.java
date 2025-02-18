package br.com.fiap.api_patient.core.port.out;

import br.com.fiap.api_patient.core.domain.Patient;
import br.com.fiap.api_patient.core.domain.Cpf;

import java.util.List;
import java.util.Optional;

public interface PatientPortOut {

    Patient createPatient(Patient patient);

    Optional<Patient> getPatientById(Long patientId);

    Optional<Patient> getPatientByEmail(String email);

    List<Patient> getAllPatients();

    Patient updatePatient(Patient outdatedPatient);

    void deletePatientById(Long patientId);

    boolean existsPatientById(Long patientId);

    boolean existsPatientByEmail(String email);

    boolean existsPatientByCpf(Cpf cpf);
}
