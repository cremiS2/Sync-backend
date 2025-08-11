package com.projeto.tcc.entities;

import com.projeto.tcc.dto.LoginInformacoes;
import com.projeto.tcc.dto.entrada.FuncionarioDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Entity
@Table(name = "tb_funcionarios")
@Data
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;
    private String nome;
    private Integer matricula;
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