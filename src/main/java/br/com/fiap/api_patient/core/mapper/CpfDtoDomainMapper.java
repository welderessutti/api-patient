package br.com.fiap.api_patient.core.mapper;

import br.com.fiap.api_patient.core.domain.Cpf;
import br.com.fiap.api_patient.framework.dto.request.create.CpfCreateRequestDto;
import br.com.fiap.api_patient.framework.dto.request.update.CpfUpdateRequestDto;
import br.com.fiap.api_patient.framework.dto.response.CpfResponseDto;

import static java.util.Objects.nonNull;

public class CpfDtoDomainMapper {

    private CpfDtoDomainMapper() {
    }

    public static Cpf toCpf(CpfCreateRequestDto request) {
        Cpf cpf = new Cpf();
        cpf.setDocumentNumber(request.getDocumentNumber());
        return cpf;
    }

    public static CpfResponseDto toCpfResponseDto(Cpf cpf) {
        CpfResponseDto cpfResponseDTO = new CpfResponseDto();
        cpfResponseDTO.setDocumentNumber(cpf.getDocumentNumber());
        return cpfResponseDTO;
    }

    public static Cpf updateRequestToCpf(CpfUpdateRequestDto request) {
        Cpf cpf = new Cpf();
        if (nonNull(request.getDocumentNumber())) {
            cpf.setDocumentNumber(request.getDocumentNumber());
        }
        return cpf;
    }
}
