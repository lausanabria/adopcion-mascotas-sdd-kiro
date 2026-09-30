package com.project.sdd.application.mapper;

import com.project.sdd.application.dto.response.PetResponseDTO;
import com.project.sdd.domain.entities.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {
    
    public PetResponseDTO toResponseDTO(Pet pet) {
        if (pet == null) {
            return null;
        }
        
        return PetResponseDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .age(pet.getAge())
                .petType(pet.getPetType())
                .breed(pet.getBreed())
                .birthdate(pet.getBirthdate())
                .vaccines(pet.getVaccinesAsEnum())
                .build();
    }
}
