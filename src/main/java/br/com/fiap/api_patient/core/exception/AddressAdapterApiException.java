package br.com.fiap.api_patient.core.exception;

public class AddressAdapterApiException extends RuntimeException {

    public AddressAdapterApiException(String message) {
        super(message);
    }

    public static class BadRequest extends AddressAdapterApiException {
        public BadRequest(String message) {
            super(message);
        }
    }

    public static class NotFound extends AddressAdapterApiException {
        public NotFound(String message) {
            super(message);
        }
    }

    public static class InternalError extends AddressAdapterApiException {
        public InternalError(String message) {
            super(message);
        }
    }
}
