package com.project.sdd.application.dto.response;

import com.project.sdd.domain.enums.ApplicationStatus;
import com.project.sdd.domain.enums.HousingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionApplicationResponseDTO {
    private Long id;
    private ApplicantResponseDTO applicant;
    private PetResponseDTO pet;
    private HousingType housingType;
    private Boolean hasOtherPets;
    private String occupation;
    private ApplicationStatus status;
    private LocalDateTime applicationDate;
}
