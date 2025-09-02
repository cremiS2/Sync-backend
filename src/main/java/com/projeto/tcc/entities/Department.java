package com.projeto.tcc.entities;

import com.projeto.tcc.enums.StatusDepartment;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_departament")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departament_id")
    private Long id;

    private String name;

    private String description;

    private String location;

    @Enumerated(EnumType.STRING)
    private StatusDepartment status;


    @CreatedDate
    private LocalDateTime createdAt;

    private BigDecimal budget;

    @OneToMany(mappedBy = "department")
    private List<Sector> sectors;

}
