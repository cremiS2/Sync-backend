package com.projeto.tcc.entities;

import com.projeto.tcc.enuns.StatusEmployee;
import com.projeto.tcc.enuns.Shift;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "tb_employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    private String photo;

    private Integer employeeID;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "tb_employee_roles",
            joinColumns =  @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    Shift shift;

    @Enumerated(EnumType.STRING)
    private StatusEmployee status;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    private Boolean availability = true;
}