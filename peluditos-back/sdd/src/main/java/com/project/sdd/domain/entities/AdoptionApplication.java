package com.project.sdd.domain.entities;

import com.project.sdd.domain.enums.ApplicationStatus;
import com.project.sdd.domain.enums.HousingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "adoption_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "housing_type")
    private HousingType housingType;
    
    @Column(name = "has_other_pets")
    private Boolean hasOtherPets;
    
    @Column(length = 100)
    private String occupation;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.PENDING;
    
    @Column(name = "application_date", nullable = false)
    private LocalDateTime applicationDate = LocalDateTime.now();
    
    @PrePersist
    protected void onCreate() {
        if (applicationDate == null) {
            applicationDate = LocalDateTime.now();
        }
        if (status == null) {
            status = ApplicationStatus.PENDING;
        }
    }
}
