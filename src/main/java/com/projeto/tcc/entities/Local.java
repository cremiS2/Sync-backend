package com.projeto.tcc.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tb_local")
@Data
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "local_id")
    private Long id;

    @Embedded
    private Endereco endereco;

    private String nomeUnidade;

    @OneToMany(mappedBy = "localMaquina", fetch = FetchType.LAZY)
    private List<Maquina> maquinasLocal;

    private Integer capacidadeMaquinas;
}
