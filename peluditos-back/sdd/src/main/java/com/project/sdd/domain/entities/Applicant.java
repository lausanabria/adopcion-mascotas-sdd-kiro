package com.project.sdd.domain.entities;

import com.project.sdd.domain.enums.DocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "applicants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;
    
    @Column(name = "document_number", nullable = false, unique = true, length = 50)
    private String documentNumber;
    
    @Column(nullable = false, length = 150)
    private String name;
    
    @Column(nullable = false, length = 150)
    private String email;
    
    @Column(name = "phone_number", length = 50)
    private String phoneNumber;
}
