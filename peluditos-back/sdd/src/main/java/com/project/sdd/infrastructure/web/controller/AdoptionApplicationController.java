package com.project.sdd.infrastructure.web.controller;

import com.project.sdd.application.dto.request.AdoptionApplicationRequestDTO;
import com.project.sdd.application.dto.response.AdoptionApplicationResponseDTO;
import com.project.sdd.application.service.AdoptionApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@Slf4j
public class AdoptionApplicationController {
    
    private final AdoptionApplicationService adoptionApplicationService;
    
    @PostMapping
    public ResponseEntity<AdoptionApplicationResponseDTO> createApplication(
            @Valid @RequestBody AdoptionApplicationRequestDTO requestDTO) {
        
        log.info("Recibida solicitud de adopción para mascota ID: {}", requestDTO.getPetId());
        AdoptionApplicationResponseDTO response = adoptionApplicationService.createAdoptionApplication(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
