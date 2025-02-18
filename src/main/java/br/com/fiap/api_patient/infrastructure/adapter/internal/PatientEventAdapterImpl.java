package br.com.fiap.api_patient.infrastructure.adapter.internal;

import br.com.fiap.api_patient.core.domain.Patient;
import br.com.fiap.api_patient.core.port.out.PatientEventPortOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;

@Slf4j
public class PatientEventAdapterImpl implements PatientEventPortOut {

    private final StreamBridge streamBridge;

    public PatientEventAdapterImpl(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public void sendPatientCreatedEvent(Patient patient) {
        streamBridge.send("patientCreatedSupplier-out-0", MessageBuilder.withPayload(patient).build());
        log.info("Created patient event sent: {}", patient.getId());
    }
}
