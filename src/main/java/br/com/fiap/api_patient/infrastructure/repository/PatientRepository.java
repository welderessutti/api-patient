package br.com.fiap.api_patient.infrastructure.repository;

import br.com.fiap.api_patient.infrastructure.entity.PatientEntity;
import br.com.fiap.api_patient.infrastructure.entity.CpfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByCpf(CpfEntity cpf);
}
