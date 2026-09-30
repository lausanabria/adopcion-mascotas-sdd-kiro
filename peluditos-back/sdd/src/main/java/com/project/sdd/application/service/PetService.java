package com.project.sdd.application.service;

import com.project.sdd.application.dto.response.PetResponseDTO;
import com.project.sdd.application.mapper.PetMapper;
import com.project.sdd.domain.entities.Pet;
import com.project.sdd.domain.enums.PetType;
import com.project.sdd.infrastructure.exception.ResourceNotFoundException;
import com.project.sdd.infrastructure.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetService {
    
    private final PetRepository petRepository;
    private final PetMapper petMapper;
    
    @Transactional(readOnly = true)
    public List<PetResponseDTO> getAllPets() {
        log.info("Obteniendo todas las mascotas");
        return petRepository.findAll()
                .stream()
                .map(petMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<PetResponseDTO> getPetsByType(PetType petType) {
        log.info("Obteniendo mascotas por tipo: {}", petType);
        return petRepository.findByPetType(petType)
                .stream()
                .map(petMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public PetResponseDTO getPetById(Long id) {
        log.info("Obteniendo mascota con ID: {}", id);
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con ID: " + id));
        return petMapper.toResponseDTO(pet);
    }
    
    @Transactional(readOnly = true)
    public Pet findPetEntityById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con ID: " + id));
    }
}
