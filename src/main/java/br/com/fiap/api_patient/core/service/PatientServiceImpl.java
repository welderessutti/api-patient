package br.com.fiap.api_patient.core.service;

import br.com.fiap.api_patient.core.domain.Address;
import br.com.fiap.api_patient.core.domain.Patient;
import br.com.fiap.api_patient.core.exception.PatientAlreadyExistsException;
import br.com.fiap.api_patient.core.exception.PatientNotFoundException;
import br.com.fiap.api_patient.core.mapper.PatientDtoDomainMapper;
import br.com.fiap.api_patient.core.port.in.PatientPortIn;
import br.com.fiap.api_patient.core.port.out.PatientPortOut;
import br.com.fiap.api_patient.core.updater.PatientUpdater;
import br.com.fiap.api_patient.framework.dto.request.create.PatientCreateRequestDto;
import br.com.fiap.api_patient.framework.dto.request.update.PatientUpdateRequestDto;
import br.com.fiap.api_patient.framework.dto.response.AllPatientsResponseDto;
import br.com.fiap.api_patient.framework.dto.response.CreatePatientResponseDto;
import br.com.fiap.api_patient.framework.dto.response.PatientResponseDto;

import java.util.Optional;

import static java.util.Objects.nonNull;

public class PatientServiceImpl implements PatientPortIn {

    private final AddressService addressService;
    private final PatientPortOut patientPortOut;
    private static final String EXISTS_WITH_CPF = "Patient already exists with CPF: ";
    private static final String EXISTS_WITH_EMAIL = "Patient already exists with e-mail: ";
    private static final String NOT_FOUND_WITH_ID = "Patient not found with id: ";
    private static final String NOT_FOUND_WITH_EMAIL = "Patient not found with e-mail: ";

    public PatientServiceImpl(
            AddressService addressService,
            PatientPortOut patientPortOut
    ) {
        this.addressService = addressService;
        this.patientPortOut = patientPortOut;
    }

    @Override
    public CreatePatientResponseDto createPatient(PatientCreateRequestDto request) {
        Patient patient = PatientDtoDomainMapper.toPatient(request);
        validatePatientExistence(patient);
        Address returnedAddress = getAddressData(patient);
        patient.setAddress(returnedAddress);
        return PatientDtoDomainMapper.toCreatePatientResponseDto(patientPortOut.createPatient(patient));
    }

    @Override
    public PatientResponseDto getPatientById(Long patientId) {
        Optional<Patient> optionalPatient = patientPortOut.getPatientById(patientId);
        if (optionalPatient.isEmpty()) {
            throw new PatientNotFoundException(NOT_FOUND_WITH_ID + patientId);
        }
        return PatientDtoDomainMapper.toPatientResponseDto(optionalPatient.get());
    }

    @Override
    public PatientResponseDto getPatientByEmail(String email) {
        Optional<Patient> optionalPatient = patientPortOut.getPatientByEmail(email);
        if (optionalPatient.isEmpty()) {
            throw new PatientNotFoundException(NOT_FOUND_WITH_EMAIL + email);
        }
        return PatientDtoDomainMapper.toPatientResponseDto(optionalPatient.get());
    }

    @Override
    public AllPatientsResponseDto getAllPatients() {
        return PatientDtoDomainMapper.allPatientsResponseDto(patientPortOut.getAllPatients());
    }

    @Override
    public PatientResponseDto updatePatient(Long patientId, PatientUpdateRequestDto request) {
        Patient outdatedPatient = findPatientById(patientId);
        Patient updatedPatient = PatientDtoDomainMapper.updateRequestToPatient(request);
        updatePatientInformation(outdatedPatient, updatedPatient);
        return PatientDtoDomainMapper.toPatientResponseDto(patientPortOut.updatePatient(outdatedPatient));
    }

    @Override
    public void deletePatientById(Long patientId) {
        if (!patientPortOut.existsPatientById(patientId)) {
            throw new PatientNotFoundException(NOT_FOUND_WITH_ID + patientId);
        }
        patientPortOut.deletePatientById(patientId);
    }

    private void validatePatientExistence(Patient patient) {
        if (patientPortOut.existsPatientByCpf(patient.getCpf())) {
            throw new PatientAlreadyExistsException(EXISTS_WITH_CPF + patient.getCpf().getDocumentNumber());
        }
        if (patientPortOut.existsPatientByEmail(patient.getEmail())) {
            throw new PatientAlreadyExistsException(EXISTS_WITH_EMAIL + patient.getEmail());
        }
    }

    private Address getAddressData(Patient patient) {
        return addressService.getAddressByApi(patient.getAddress());
    }

    private Patient findPatientById(Long patientId) {
        return patientPortOut.getPatientById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(NOT_FOUND_WITH_ID + patientId));
    }

    private void updatePatientInformation(Patient outdatedPatient, Patient updatedPatient) {
        validatePatientUniqueness(updatedPatient);
        if (nonNull(updatedPatient.getAddress()) && nonNull(updatedPatient.getAddress().getCep())) {
            Address returnedAddress = getAddressData(updatedPatient);
            updatedPatient.setAddress(returnedAddress);
        }
        PatientUpdater.updateOutdatedPatient(outdatedPatient, updatedPatient);
    }

    private void validatePatientUniqueness(Patient updatedPatient) {
        if (nonNull(updatedPatient.getCpf()) && patientPortOut.existsPatientByCpf(updatedPatient.getCpf())) {
            throw new PatientAlreadyExistsException(
                    EXISTS_WITH_CPF + updatedPatient.getCpf().getDocumentNumber());
        }
        if (nonNull(updatedPatient.getEmail()) && patientPortOut.existsPatientByEmail(updatedPatient.getEmail())) {
            throw new PatientAlreadyExistsException(
                    EXISTS_WITH_EMAIL + updatedPatient.getEmail());
        }
    }
}
