package br.com.fiap.api_patient.infrastructure.api;

import br.com.fiap.api_patient.infrastructure.dto.ViaCepApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCepApiClient", url = "https://viacep.com.br/ws")
public interface ViaCepApiClient {

    @GetMapping("/{cep}/json")
    ViaCepApiResponseDto getAddressByCep(@PathVariable String cep);
}
