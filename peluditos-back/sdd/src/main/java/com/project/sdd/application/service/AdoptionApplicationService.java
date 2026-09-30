package com.project.sdd.application.service;

import com.project.sdd.application.dto.request.AdoptionApplicationRequestDTO;
import com.project.sdd.application.dto.response.AdoptionApplicationResponseDTO;
import com.project.sdd.application.mapper.AdoptionApplicationMapper;
import com.project.sdd.domain.entities.AdoptionApplication;
import com.project.sdd.domain.entities.Applicant;
import com.project.sdd.domain.entities.Pet;
import com.project.sdd.infrastructure.repository.AdoptionApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdoptionApplicationService {
    
    private final AdoptionApplicationRepository adoptionApplicationRepository;
    private final AdoptionApplicationMapper adoptionApplicationMapper;
    private final ApplicantService applicantService;
    private final PetService petService;
    
    @Transactional
    public AdoptionApplicationResponseDTO createAdoptionApplication(AdoptionApplicationRequestDTO requestDTO) {
        log.info("Creando solicitud de adopción para mascota ID: {}", requestDTO.getPetId());
        
        // Crear o recuperar el solicitante
        Applicant applicant = applicantService.createOrGetApplicant(requestDTO.getApplicant());
        
        // Obtener la mascota
        Pet pet = petService.findPetEntityById(requestDTO.getPetId());
        
        // Crear la solicitud de adopción
        AdoptionApplication application = adoptionApplicationMapper.toEntity(requestDTO, applicant, pet);
        AdoptionApplication savedApplication = adoptionApplicationRepository.save(application);
        
        log.info("Solicitud de adopción creada con ID: {}", savedApplication.getId());
        
        return adoptionApplicationMapper.toResponseDTO(savedApplication);
    }
}
