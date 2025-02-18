package br.com.fiap.api_patient.core.updater;

import br.com.fiap.api_patient.core.domain.Cpf;

import static java.util.Objects.nonNull;

public class CpfUpdater {

    private CpfUpdater() {
    }

    public static void updateOutdatedCpf(Cpf outdated, Cpf updated) {
        if (nonNull(updated.getDocumentNumber())) {
            outdated.setDocumentNumber(updated.getDocumentNumber());
        }
    }
}
