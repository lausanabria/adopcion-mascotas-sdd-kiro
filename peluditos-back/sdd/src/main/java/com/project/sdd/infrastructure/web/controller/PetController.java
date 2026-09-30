package com.project.sdd.infrastructure.web.controller;

import com.project.sdd.application.dto.response.PetResponseDTO;
import com.project.sdd.application.service.PetService;
import com.project.sdd.domain.enums.PetType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
@Slf4j
public class PetController {
    
    private final PetService petService;
    
    @GetMapping
    public ResponseEntity<List<PetResponseDTO>> getAllPets(
            @RequestParam(required = false) PetType petType) {
        
        log.info("Recibida solicitud para obtener mascotas. Filtro: {}", petType);
        
        List<PetResponseDTO> pets;
        if (petType != null) {
            pets = petService.getPetsByType(petType);
        } else {
            pets = petService.getAllPets();
        }
        
        return ResponseEntity.ok(pets);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDTO> getPetById(@PathVariable Long id) {
        log.info("Recibida solicitud para obtener mascota con ID: {}", id);
        PetResponseDTO pet = petService.getPetById(id);
        return ResponseEntity.ok(pet);
    }
}
