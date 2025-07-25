package com.projeto.tcc.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "tb_funcionarios")
@Data
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Long id;
    private String nome;
    private String matricula;
    @ManyToMany
    @JoinTable(
            name = "tb_funcionario_roles",
            joinColumns =  @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "escala_id")
    EscalaFuncionario escalaFuncionario;

    @ManyToOne
    @JoinColumn(name = "setor_id")
    private Setor setor;

    @OneToOne(mappedBy = "funcionarioOperando")
    private Maquina maquinaOperada;
}