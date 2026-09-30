package com.project.sdd.application.dto.response;

import com.project.sdd.domain.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantResponseDTO {
    private UUID id;
    private DocumentType documentType;
    private String documentNumber;
    private String name;
    private String email;
    private String phoneNumber;
}
