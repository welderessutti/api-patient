package br.com.fiap.api_patient.core.port.out;

import br.com.fiap.api_patient.core.domain.Address;

public interface AddressPortOut {

    Address getAddressByApi(String cep);
}
