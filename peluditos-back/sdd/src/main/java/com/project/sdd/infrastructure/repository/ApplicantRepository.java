package com.project.sdd.infrastructure.repository;

import com.project.sdd.domain.entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, UUID> {
    Optional<Applicant> findByDocumentNumber(String documentNumber);
    boolean existsByDocumentNumber(String documentNumber);
}
