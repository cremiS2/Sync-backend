package com.projeto.tcc.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tb_setor")
@Data
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setor_id")
    private Long id;
    private String name;

    private Float efficiency;

    @OneToMany(mappedBy = "setor")
    private List<Funcionario> employees;

    @OneToMany(mappedBy = "setor", fetch = FetchType.LAZY)
    private List<Maquina> machines;

    @ManyToOne
    private Departamento departament;

}

