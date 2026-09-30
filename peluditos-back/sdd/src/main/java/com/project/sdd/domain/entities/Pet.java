package com.project.sdd.domain.entities;

import com.project.sdd.domain.enums.PetType;
import com.project.sdd.domain.enums.VaccineType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    private Integer age;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "pet_type", nullable = false)
    private PetType petType;
    
    @Column(length = 100)
    private String breed;
    
    private LocalDate birthdate;
    
    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "vaccines", columnDefinition = "vaccine_type[]")
    private List<String> vaccines = new ArrayList<>();
    
    public List<VaccineType> getVaccinesAsEnum() {
        if (vaccines == null || vaccines.isEmpty()) {
            return new ArrayList<>();
        }
        return vaccines.stream()
                .map(VaccineType::valueOf)
                .toList();
    }
    
    public void setVaccinesFromEnum(List<VaccineType> vaccineTypes) {
        if (vaccineTypes == null) {
            this.vaccines = new ArrayList<>();
        } else {
            this.vaccines = vaccineTypes.stream()
                    .map(Enum::name)
                    .toList();
        }
    }
}
