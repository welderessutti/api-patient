package br.com.fiap.api_patient.core.exception;

public class InvalidCpfDocumentNumberException extends RuntimeException {

    public InvalidCpfDocumentNumberException(String message) {
        super(message);
    }
}
