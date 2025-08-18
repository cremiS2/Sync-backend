package com.projeto.tcc.entities;

import com.projeto.tcc.enuns.DisponibilidadeFuncionario;
import com.projeto.tcc.enuns.Turno;
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

    private String foto_url;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    private String nome;

    @ManyToMany
    @JoinTable(
            name = "tb_funcionario_roles",
            joinColumns =  @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    Turno turno;

    @Enumerated(EnumType.STRING)
    private DisponibilidadeFuncionario disponibilidadeFuncionario;

    @ManyToOne
    @JoinColumn(name = "setor_id")
    private Setor setor;

    @ManyToOne
    private Maquina maquinaOperada;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

}