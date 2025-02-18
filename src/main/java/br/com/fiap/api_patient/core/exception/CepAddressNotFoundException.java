package br.com.fiap.api_patient.core.exception;

public class CepAddressNotFoundException extends RuntimeException {

    public CepAddressNotFoundException(String message) {
        super(message);
    }
}
