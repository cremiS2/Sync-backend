package com.projeto.tcc.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_setor")
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setor_id")
    private Long id;
    private String nome;
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "funcionario_setor",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "setor_id")
    )
    private List<Funcionario> funcionarios;


    public Setor(Long id, String nome, String descricao, List<Funcionario> funcionarios) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.funcionarios = funcionarios;
    }

    public Setor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}

