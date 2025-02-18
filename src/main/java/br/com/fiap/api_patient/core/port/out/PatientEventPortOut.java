package br.com.fiap.api_patient.core.port.out;

import br.com.fiap.api_patient.core.domain.Patient;

public interface PatientEventPortOut {

    void sendPatientCreatedEvent(Patient patient);
}
