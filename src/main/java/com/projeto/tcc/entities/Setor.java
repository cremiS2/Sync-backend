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
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "setor")
    private List<Funcionario> funcionarios;

    @OneToMany(mappedBy = "setor", fetch = FetchType.LAZY)
    private List<Maquina> maquinas;

    @ManyToOne
    private Departamento departamento;

}

