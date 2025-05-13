package com.projeto.tcc.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id  ")
    private Long id;
    private String nome;

    public Funcionario(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Funcionario() {
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
}
