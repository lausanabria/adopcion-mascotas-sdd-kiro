package com.project.sdd.application.dto.response;

import com.project.sdd.domain.enums.PetType;
import com.project.sdd.domain.enums.VaccineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetResponseDTO {
    private Long id;
    private String name;
    private Integer age;
    private PetType petType;
    private String breed;
    private LocalDate birthdate;
    private List<VaccineType> vaccines;
}
