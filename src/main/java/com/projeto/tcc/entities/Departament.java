package com.projeto.tcc.entities;

import com.projeto.tcc.enuns.StatusDepartament;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tb_departament")
@Data
@EnableJpaAuditing()
public class Departament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departament_id")
    private Long id;

    private String name;

    private String description;

    private String location;

    @Enumerated(EnumType.STRING)
    private StatusDepartament status;


    @CreatedDate
    private LocalDateTime createdAt;

    private BigDecimal budget;

    @OneToMany(mappedBy = "departament")
    private Set<Sector> sectors;

}
