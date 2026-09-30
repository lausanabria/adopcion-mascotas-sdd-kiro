package com.project.sdd.application.service;

import com.project.sdd.application.dto.request.ApplicantRequestDTO;
import com.project.sdd.application.mapper.ApplicantMapper;
import com.project.sdd.domain.entities.Applicant;
import com.project.sdd.infrastructure.exception.DuplicateResourceException;
import com.project.sdd.infrastructure.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicantService {
    
    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;
    
    @Transactional
    public Applicant createOrGetApplicant(ApplicantRequestDTO dto) {
        log.info("Procesando solicitante con documento: {}", dto.getDocumentNumber());
        
        // Verificar si el solicitante ya existe
        return applicantRepository.findByDocumentNumber(dto.getDocumentNumber())
                .orElseGet(() -> {
                    log.info("Creando nuevo solicitante con documento: {}", dto.getDocumentNumber());
                    Applicant newApplicant = applicantMapper.toEntity(dto);
                    return applicantRepository.save(newApplicant);
                });
    }
    
    @Transactional(readOnly = true)
    public boolean existsByDocumentNumber(String documentNumber) {
        return applicantRepository.existsByDocumentNumber(documentNumber);
    }
}
