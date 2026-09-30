package com.project.sdd.application.service;

import com.project.sdd.application.dto.request.ApplicantRequestDTO;
import com.project.sdd.application.mapper.ApplicantMapper;
import com.project.sdd.domain.entities.Applicant;
import com.project.sdd.domain.enums.DocumentType;
import com.project.sdd.infrastructure.repository.ApplicantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicantServiceTest {
    
    @Mock
    private ApplicantRepository applicantRepository;
    
    @Mock
    private ApplicantMapper applicantMapper;
    
    @InjectMocks
    private ApplicantService applicantService;
    
    private ApplicantRequestDTO applicantRequestDTO;
    private Applicant applicant;
    
    @BeforeEach
    void setUp() {
        applicantRequestDTO = new ApplicantRequestDTO();
        applicantRequestDTO.setDocumentType(DocumentType.CC);
        applicantRequestDTO.setDocumentNumber("1018123456");
        applicantRequestDTO.setName("Laura Restrepo");
        applicantRequestDTO.setEmail("laura.restrepo@example.com");
        applicantRequestDTO.setPhoneNumber("3001234567");
        
        applicant = new Applicant();
        applicant.setId(UUID.randomUUID());
        applicant.setDocumentType(DocumentType.CC);
        applicant.setDocumentNumber("1018123456");
        applicant.setName("Laura Restrepo");
        applicant.setEmail("laura.restrepo@example.com");
        applicant.setPhoneNumber("3001234567");
    }
    
    @Test
    @DisplayName("Debe crear un nuevo solicitante cuando no existe")
    void testCreateOrGetApplicant_NewApplicant() {
        // Arrange
        when(applicantRepository.findByDocumentNumber("1018123456")).thenReturn(Optional.empty());
        when(applicantMapper.toEntity(applicantRequestDTO)).thenReturn(applicant);
        when(applicantRepository.save(any(Applicant.class))).thenReturn(applicant);
        
        // Act
        Applicant result = applicantService.createOrGetApplicant(applicantRequestDTO);
        
        // Assert
        assertNotNull(result);
        assertEquals("1018123456", result.getDocumentNumber());
        assertEquals("Laura Restrepo", result.getName());
        verify(applicantRepository, times(1)).findByDocumentNumber("1018123456");
        verify(applicantMapper, times(1)).toEntity(applicantRequestDTO);
        verify(applicantRepository, times(1)).save(any(Applicant.class));
    }
    
    @Test
    @DisplayName("Debe retornar solicitante existente cuando ya existe")
    void testCreateOrGetApplicant_ExistingApplicant() {
        // Arrange
        when(applicantRepository.findByDocumentNumber("1018123456")).thenReturn(Optional.of(applicant));
        
        // Act
        Applicant result = applicantService.createOrGetApplicant(applicantRequestDTO);
        
        // Assert
        assertNotNull(result);
        assertEquals("1018123456", result.getDocumentNumber());
        assertEquals("Laura Restrepo", result.getName());
        verify(applicantRepository, times(1)).findByDocumentNumber("1018123456");
        verify(applicantMapper, never()).toEntity(any());
        verify(applicantRepository, never()).save(any());
    }
    
    @Test
    @DisplayName("Debe verificar si existe solicitante por número de documento")
    void testExistsByDocumentNumber() {
        // Arrange
        when(applicantRepository.existsByDocumentNumber("1018123456")).thenReturn(true);
        
        // Act
        boolean result = applicantService.existsByDocumentNumber("1018123456");
        
        // Assert
        assertTrue(result);
        verify(applicantRepository, times(1)).existsByDocumentNumber("1018123456");
    }
}
