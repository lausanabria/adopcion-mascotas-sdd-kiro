package com.project.sdd.application.dto.request;

import com.project.sdd.domain.enums.HousingType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionApplicationRequestDTO {
    
    @NotNull(message = "Los datos del solicitante son obligatorios")
    @Valid
    private ApplicantRequestDTO applicant;
    
    @NotNull(message = "El ID de la mascota es obligatorio")
    private Long petId;
    
    private HousingType housingType;
    
    private Boolean hasOtherPets;
    
    private String occupation;
}
