package com.project.sdd.application.dto.request;

import com.project.sdd.domain.enums.DocumentType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantRequestDTO {
    
    @NotNull(message = "El tipo de documento es obligatorio")
    private DocumentType documentType;
    
    @NotBlank(message = "El número de documento es obligatorio")
    private String documentNumber;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;
    
    private String phoneNumber;
}
