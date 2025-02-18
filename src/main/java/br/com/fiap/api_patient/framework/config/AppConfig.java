package br.com.fiap.api_patient.framework.config;

import br.com.fiap.api_patient.core.port.in.PatientPortIn;
import br.com.fiap.api_patient.core.port.out.AddressPortOut;
import br.com.fiap.api_patient.core.port.out.PatientEventPortOut;
import br.com.fiap.api_patient.core.port.out.PatientPortOut;
import br.com.fiap.api_patient.core.service.AddressService;
import br.com.fiap.api_patient.core.service.PatientServiceImpl;
import br.com.fiap.api_patient.infrastructure.adapter.external.AddressAdapterImpl;
import br.com.fiap.api_patient.infrastructure.adapter.internal.PatientAdapterImpl;
import br.com.fiap.api_patient.infrastructure.adapter.internal.PatientEventAdapterImpl;
import br.com.fiap.api_patient.infrastructure.api.ViaCepApiClient;
import br.com.fiap.api_patient.infrastructure.repository.PatientRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PatientPortOut patientAdapter(PatientRepository patientRepository) {
        return new PatientAdapterImpl(patientRepository);
    }

    @Bean
    public PatientEventPortOut patientEventAdapter(StreamBridge streamBridge) {
        return new PatientEventAdapterImpl(streamBridge);
    }

    @Bean
    public PatientPortIn patientService(
            AddressService addressService,
            PatientPortOut patientPortOut,
            PatientEventPortOut patientEventPortOut) {
        return new PatientServiceImpl(addressService, patientPortOut, patientEventPortOut);
    }

    @Bean
    public AddressPortOut addressAdapter(ViaCepApiClient viaCepApiClient) {
        return new AddressAdapterImpl(viaCepApiClient);
    }

    @Bean
    public AddressService addressService(AddressPortOut addressPortOut) {
        return new AddressService(addressPortOut);
    }
}
