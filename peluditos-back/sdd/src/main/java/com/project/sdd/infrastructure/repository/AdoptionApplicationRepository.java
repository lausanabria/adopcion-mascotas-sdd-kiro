package com.project.sdd.infrastructure.repository;

import com.project.sdd.domain.entities.AdoptionApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionApplicationRepository extends JpaRepository<AdoptionApplication, Long> {
}
