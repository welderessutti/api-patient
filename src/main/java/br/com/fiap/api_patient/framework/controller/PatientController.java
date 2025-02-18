package br.com.fiap.api_patient.framework.controller;

import br.com.fiap.api_patient.core.port.in.PatientPortIn;
import br.com.fiap.api_patient.framework.dto.request.create.PatientCreateRequestDto;
import br.com.fiap.api_patient.framework.dto.request.update.PatientUpdateRequestDto;
import br.com.fiap.api_patient.framework.dto.response.AllPatientsResponseDto;
import br.com.fiap.api_patient.framework.dto.response.CreatePatientResponseDto;
import br.com.fiap.api_patient.framework.dto.response.PatientResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientPortIn patientPortIn;

    public PatientController(PatientPortIn patientPortIn) {
        this.patientPortIn = patientPortIn;
    }

    @PostMapping
    public ResponseEntity<CreatePatientResponseDto> createPatient(@RequestBody @Valid PatientCreateRequestDto request) {
        CreatePatientResponseDto createPatientResponseDto = patientPortIn.createPatient(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{patientId}")
                .buildAndExpand(createPatientResponseDto.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(createPatientResponseDto);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long patientId) {
        return ResponseEntity.status(HttpStatus.OK).body(patientPortIn.getPatientById(patientId));
    }

    @GetMapping(params = "email")
    public ResponseEntity<PatientResponseDto> getPatientByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(patientPortIn.getPatientByEmail(email));
    }

    @GetMapping
    public ResponseEntity<AllPatientsResponseDto> getAllPatients() {
        return ResponseEntity.status(HttpStatus.OK).body(patientPortIn.getAllPatients());
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResponseDto> updatePatient(
            @PathVariable Long patientId, @RequestBody @Valid PatientUpdateRequestDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(patientPortIn.updatePatient(patientId, request));
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatientById(@PathVariable Long patientId) {
        patientPortIn.deletePatientById(patientId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
