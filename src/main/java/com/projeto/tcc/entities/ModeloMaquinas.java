package com.projeto.tcc.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ModeloMaquinas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modelo_maquina_id")
    private Long id;
    private String nomeModelo;
    private String descricaoModelo;

    @OneToMany(mappedBy = "modeloMaquina", fetch = FetchType.LAZY)
    private List<Maquina> maquinas;
}
