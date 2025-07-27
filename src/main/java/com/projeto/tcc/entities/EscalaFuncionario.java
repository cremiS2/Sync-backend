package com.projeto.tcc.entities;

import com.projeto.tcc.enuns.Turno;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "tb_escala_funcionarios")
@Data
public class EscalaFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "escalaFuncionario")
    private List<Funcionario> funcionarios;

    @Enumerated(EnumType.STRING)
    private Turno turnoFuncionario;


    private LocalTime horarioInicioJornada;

    private LocalTime horarioPausaAlmoco;

    private LocalTime horarioRetornoAlmoco;

    private LocalTime horarioFimJornada;




}
