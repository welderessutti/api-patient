package br.com.fiap.api_patient.infrastructure.adapter.external;

import br.com.fiap.api_patient.core.domain.Address;
import br.com.fiap.api_patient.core.exception.AddressAdapterApiException;
import br.com.fiap.api_patient.core.port.out.AddressPortOut;
import br.com.fiap.api_patient.infrastructure.api.ViaCepApiClient;
import br.com.fiap.api_patient.infrastructure.dto.ViaCepApiResponseDto;
import br.com.fiap.api_patient.infrastructure.mapper.AddressDomainDtoMapper;
import feign.FeignException;

public class AddressAdapterImpl implements AddressPortOut {

    private final ViaCepApiClient viaCepApiClient;

    public AddressAdapterImpl(ViaCepApiClient viaCepApiClient) {
        this.viaCepApiClient = viaCepApiClient;
    }

    @Override
    public Address getAddressByApi(String cep) {
        try {
            ViaCepApiResponseDto viaCepApiResponseDto = viaCepApiClient.getAddressByCep(cep);
            if (Boolean.TRUE.equals(viaCepApiResponseDto.getErro())) {
                throw new AddressAdapterApiException.NotFound(cep);
            }
            return AddressDomainDtoMapper.toAddress(viaCepApiResponseDto);
        } catch (FeignException.BadRequest e) {
            throw new AddressAdapterApiException.BadRequest(cep);
        } catch (FeignException e) {
            throw new AddressAdapterApiException.InternalError("Something went wrong with ViaCep API");
        }
    }
}
