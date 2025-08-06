package com.projeto.tcc.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tb_sector")
@Data
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sector_id")
    private Long id;
    private String name;

    private Float efficiency;

    @OneToMany(mappedBy = "sector")
    private List<Employee> employees;

    @OneToMany(mappedBy = "sector", fetch = FetchType.LAZY)
    private List<Machine> machines;

    @ManyToOne
    private Departament departament;

}

