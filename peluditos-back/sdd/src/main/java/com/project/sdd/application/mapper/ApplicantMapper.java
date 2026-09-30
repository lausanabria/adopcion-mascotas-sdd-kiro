package com.project.sdd.application.mapper;

import com.project.sdd.application.dto.request.ApplicantRequestDTO;
import com.project.sdd.application.dto.response.ApplicantResponseDTO;
import com.project.sdd.domain.entities.Applicant;
import org.springframework.stereotype.Component;

@Component
public class ApplicantMapper {
    
    public Applicant toEntity(ApplicantRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Applicant applicant = new Applicant();
        applicant.setDocumentType(dto.getDocumentType());
        applicant.setDocumentNumber(dto.getDocumentNumber());
        applicant.setName(dto.getName());
        applicant.setEmail(dto.getEmail());
        applicant.setPhoneNumber(dto.getPhoneNumber());
        
        return applicant;
    }
    
    public ApplicantResponseDTO toResponseDTO(Applicant applicant) {
        if (applicant == null) {
            return null;
        }
        
        return ApplicantResponseDTO.builder()
                .id(applicant.getId())
                .documentType(applicant.getDocumentType())
                .documentNumber(applicant.getDocumentNumber())
                .name(applicant.getName())
                .email(applicant.getEmail())
                .phoneNumber(applicant.getPhoneNumber())
                .build();
    }
}
