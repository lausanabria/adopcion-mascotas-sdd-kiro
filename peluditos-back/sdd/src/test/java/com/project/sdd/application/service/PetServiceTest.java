package com.project.sdd.application.service;

import com.project.sdd.application.dto.response.PetResponseDTO;
import com.project.sdd.application.mapper.PetMapper;
import com.project.sdd.domain.entities.Pet;
import com.project.sdd.domain.enums.PetType;
import com.project.sdd.domain.enums.VaccineType;
import com.project.sdd.infrastructure.exception.ResourceNotFoundException;
import com.project.sdd.infrastructure.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {
    
    @Mock
    private PetRepository petRepository;
    
    @Mock
    private PetMapper petMapper;
    
    @InjectMocks
    private PetService petService;
    
    private Pet pet;
    private PetResponseDTO petResponseDTO;
    
    @BeforeEach
    void setUp() {
        pet = new Pet();
        pet.setId(1L);
        pet.setName("Luna");
        pet.setAge(2);
        pet.setPetType(PetType.PERRO);
        pet.setBreed("Criolla");
        pet.setBirthdate(LocalDate.of(2024, 3, 15));
        pet.setVaccinesFromEnum(Arrays.asList(VaccineType.RABIA, VaccineType.MOQUILLO_CANINO));
        
        petResponseDTO = PetResponseDTO.builder()
                .id(1L)
                .name("Luna")
                .age(2)
                .petType(PetType.PERRO)
                .breed("Criolla")
                .birthdate(LocalDate.of(2024, 3, 15))
                .vaccines(Arrays.asList(VaccineType.RABIA, VaccineType.MOQUILLO_CANINO))
                .build();
    }
    
    @Test
    @DisplayName("Debe obtener todas las mascotas exitosamente")
    void testGetAllPets() {
        // Arrange
        List<Pet> pets = Arrays.asList(pet);
        when(petRepository.findAll()).thenReturn(pets);
        when(petMapper.toResponseDTO(any(Pet.class))).thenReturn(petResponseDTO);
        
        // Act
        List<PetResponseDTO> result = petService.getAllPets();
        
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Luna", result.get(0).getName());
        verify(petRepository, times(1)).findAll();
        verify(petMapper, times(1)).toResponseDTO(any(Pet.class));
    }
    
    @Test
    @DisplayName("Debe obtener mascotas filtradas por tipo")
    void testGetPetsByType() {
        // Arrange
        List<Pet> dogs = Arrays.asList(pet);
        when(petRepository.findByPetType(PetType.PERRO)).thenReturn(dogs);
        when(petMapper.toResponseDTO(any(Pet.class))).thenReturn(petResponseDTO);
        
        // Act
        List<PetResponseDTO> result = petService.getPetsByType(PetType.PERRO);
        
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(PetType.PERRO, result.get(0).getPetType());
        verify(petRepository, times(1)).findByPetType(PetType.PERRO);
        verify(petMapper, times(1)).toResponseDTO(any(Pet.class));
    }
    
    @Test
    @DisplayName("Debe obtener una mascota por ID exitosamente")
    void testGetPetById() {
        // Arrange
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(petMapper.toResponseDTO(pet)).thenReturn(petResponseDTO);
        
        // Act
        PetResponseDTO result = petService.getPetById(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Luna", result.getName());
        verify(petRepository, times(1)).findById(1L);
        verify(petMapper, times(1)).toResponseDTO(pet);
    }
    
    @Test
    @DisplayName("Debe lanzar excepción cuando la mascota no existe")
    void testGetPetById_NotFound() {
        // Arrange
        when(petRepository.findById(999L)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            petService.getPetById(999L);
        });
        verify(petRepository, times(1)).findById(999L);
        verify(petMapper, never()).toResponseDTO(any());
    }
    
    @Test
    @DisplayName("Debe encontrar entidad Pet por ID")
    void testFindPetEntityById() {
        // Arrange
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        
        // Act
        Pet result = petService.findPetEntityById(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Luna", result.getName());
        verify(petRepository, times(1)).findById(1L);
    }
}
