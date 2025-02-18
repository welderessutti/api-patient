package br.com.fiap.api_patient.core.updater;

import br.com.fiap.api_patient.core.domain.Patient;

import static java.util.Objects.nonNull;

public class PatientUpdater {

    private PatientUpdater() {
    }

    public static void updateOutdatedPatient(Patient outdated, Patient updated) {
        if (nonNull(updated.getFullName())) {
            outdated.setFullName(updated.getFullName());
        }
        if (nonNull(updated.getEmail())) {
            outdated.setEmail(updated.getEmail());
        }
        if (nonNull(updated.getMobilePhoneNumber())) {
            outdated.setMobilePhoneNumber(updated.getMobilePhoneNumber());
        }
        if (nonNull(updated.getCpf())) {
            CpfUpdater.updateOutdatedCpf(outdated.getCpf(), updated.getCpf());
        }
        if (nonNull(updated.getAddress())) {
            AddressUpdater.updateOutdatedAddress(outdated.getAddress(), updated.getAddress());
        }
    }
}
