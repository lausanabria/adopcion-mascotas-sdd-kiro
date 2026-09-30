package com.project.sdd.infrastructure.repository;

import com.project.sdd.domain.entities.Pet;
import com.project.sdd.domain.enums.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByPetType(PetType petType);
}
