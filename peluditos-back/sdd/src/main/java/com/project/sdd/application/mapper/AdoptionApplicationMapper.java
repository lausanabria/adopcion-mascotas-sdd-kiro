package com.project.sdd.application.mapper;

import com.project.sdd.application.dto.request.AdoptionApplicationRequestDTO;
import com.project.sdd.application.dto.response.AdoptionApplicationResponseDTO;
import com.project.sdd.domain.entities.AdoptionApplication;
import com.project.sdd.domain.entities.Applicant;
import com.project.sdd.domain.entities.Pet;
import com.project.sdd.domain.enums.ApplicationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AdoptionApplicationMapper {
    
    private final ApplicantMapper applicantMapper;
    private final PetMapper petMapper;
    
    public AdoptionApplication toEntity(AdoptionApplicationRequestDTO dto, Applicant applicant, Pet pet) {
        if (dto == null) {
            return null;
        }
        
        AdoptionApplication application = new AdoptionApplication();
        application.setApplicant(applicant);
        application.setPet(pet);
        application.setHousingType(dto.getHousingType());
        application.setHasOtherPets(dto.getHasOtherPets());
        application.setOccupation(dto.getOccupation());
        application.setStatus(ApplicationStatus.PENDING);
        application.setApplicationDate(LocalDateTime.now());
        
        return application;
    }
    
    public AdoptionApplicationResponseDTO toResponseDTO(AdoptionApplication application) {
        if (application == null) {
            return null;
        }
        
        return AdoptionApplicationResponseDTO.builder()
                .id(application.getId())
                .applicant(applicantMapper.toResponseDTO(application.getApplicant()))
                .pet(petMapper.toResponseDTO(application.getPet()))
                .housingType(application.getHousingType())
                .hasOtherPets(application.getHasOtherPets())
                .occupation(application.getOccupation())
                .status(application.getStatus())
                .applicationDate(application.getApplicationDate())
                .build();
    }
}
