package com.project.sdd.application.service;

import com.project.sdd.application.dto.request.AdoptionApplicationRequestDTO;
import com.project.sdd.application.dto.request.ApplicantRequestDTO;
import com.project.sdd.application.dto.response.AdoptionApplicationResponseDTO;
import com.project.sdd.application.mapper.AdoptionApplicationMapper;
import com.project.sdd.domain.entities.AdoptionApplication;
import com.project.sdd.domain.entities.Applicant;
import com.project.sdd.domain.entities.Pet;
import com.project.sdd.domain.enums.*;
import com.project.sdd.infrastructure.repository.AdoptionApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdoptionApplicationServiceTest {
    
    @Mock
    private AdoptionApplicationRepository adoptionApplicationRepository;
    
    @Mock
    private AdoptionApplicationMapper adoptionApplicationMapper;
    
    @Mock
    private ApplicantService applicantService;
    
    @Mock
    private PetService petService;
    
    @InjectMocks
    private AdoptionApplicationService adoptionApplicationService;
    
    private AdoptionApplicationRequestDTO requestDTO;
    private ApplicantRequestDTO applicantRequestDTO;
    private Applicant applicant;
    private Pet pet;
    private AdoptionApplication adoptionApplication;
    private AdoptionApplicationResponseDTO responseDTO;
    
    @BeforeEach
    void setUp() {
        applicantRequestDTO = new ApplicantRequestDTO();
        applicantRequestDTO.setDocumentType(DocumentType.CC);
        applicantRequestDTO.setDocumentNumber("1018123456");
        applicantRequestDTO.setName("Laura Restrepo");
        applicantRequestDTO.setEmail("laura.restrepo@example.com");
        applicantRequestDTO.setPhoneNumber("3001234567");
        
        requestDTO = new AdoptionApplicationRequestDTO();
        requestDTO.setApplicant(applicantRequestDTO);
        requestDTO.setPetId(1L);
        requestDTO.setHousingType(HousingType.APARTMENT);
        requestDTO.setHasOtherPets(true);
        requestDTO.setOccupation("Ingeniera de Software");
        
        applicant = new Applicant();
        applicant.setId(UUID.randomUUID());
        applicant.setDocumentType(DocumentType.CC);
        applicant.setDocumentNumber("1018123456");
        applicant.setName("Laura Restrepo");
        applicant.setEmail("laura.restrepo@example.com");
        applicant.setPhoneNumber("3001234567");
        
        pet = new Pet();
        pet.setId(1L);
        pet.setName("Luna");
        pet.setAge(2);
        pet.setPetType(PetType.PERRO);
        pet.setBreed("Criolla");
        pet.setBirthdate(LocalDate.of(2024, 3, 15));
        pet.setVaccinesFromEnum(Arrays.asList(VaccineType.RABIA, VaccineType.MOQUILLO_CANINO));
        
        adoptionApplication = new AdoptionApplication();
        adoptionApplication.setId(1L);
        adoptionApplication.setApplicant(applicant);
        adoptionApplication.setPet(pet);
        adoptionApplication.setHousingType(HousingType.APARTMENT);
        adoptionApplication.setHasOtherPets(true);
        adoptionApplication.setOccupation("Ingeniera de Software");
        adoptionApplication.setStatus(ApplicationStatus.PENDING);
        adoptionApplication.setApplicationDate(LocalDateTime.now());
        
        responseDTO = AdoptionApplicationResponseDTO.builder()
                .id(1L)
                .housingType(HousingType.APARTMENT)
                .hasOtherPets(true)
                .occupation("Ingeniera de Software")
                .status(ApplicationStatus.PENDING)
                .applicationDate(LocalDateTime.now())
                .build();
    }
    
    @Test
    @DisplayName("Debe crear una solicitud de adopción exitosamente")
    void testCreateAdoptionApplication() {
        // Arrange
        when(applicantService.createOrGetApplicant(requestDTO.getApplicant())).thenReturn(applicant);
        when(petService.findPetEntityById(1L)).thenReturn(pet);
        when(adoptionApplicationMapper.toEntity(requestDTO, applicant, pet)).thenReturn(adoptionApplication);
        when(adoptionApplicationRepository.save(any(AdoptionApplication.class))).thenReturn(adoptionApplication);
        when(adoptionApplicationMapper.toResponseDTO(adoptionApplication)).thenReturn(responseDTO);
        
        // Act
        AdoptionApplicationResponseDTO result = adoptionApplicationService.createAdoptionApplication(requestDTO);
        
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(ApplicationStatus.PENDING, result.getStatus());
        assertEquals(HousingType.APARTMENT, result.getHousingType());
        assertTrue(result.getHasOtherPets());
        assertEquals("Ingeniera de Software", result.getOccupation());
        
        verify(applicantService, times(1)).createOrGetApplicant(requestDTO.getApplicant());
        verify(petService, times(1)).findPetEntityById(1L);
        verify(adoptionApplicationMapper, times(1)).toEntity(requestDTO, applicant, pet);
        verify(adoptionApplicationRepository, times(1)).save(any(AdoptionApplication.class));
        verify(adoptionApplicationMapper, times(1)).toResponseDTO(adoptionApplication);
    }
}
