package com.projeto.tcc.entities;

import com.projeto.tcc.enuns.StatusFuncionario;
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

    private String photo;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "tb_funcionario_roles",
            joinColumns =  @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    Turno shift;

    @Enumerated(EnumType.STRING)
    private StatusFuncionario status;

    @ManyToOne
    @JoinColumn(name = "setor_id")
    private Setor sector;

    @ManyToOne
    private Maquina machine;

}