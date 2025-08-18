//package com.projeto.tcc.entities;
//
//import com.projeto.tcc.enuns.Turno;
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Entity
//@Table(name = "tb_produtos")
//@Data
//public class Produto {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String codigoProduto;
//    private String nomeProduto;
//    private LocalDate validade;
//    @Enumerated(EnumType.STRING)
//    private Turno turnoProducao;
//
//    @ManyToMany
//    @JoinTable(
//            name = "produto_maquina",
//            joinColumns = @JoinColumn(name = "produto_id"),
//            inverseJoinColumns = @JoinColumn(name = "maquina_id")
//    )
//    private List<Maquina> maquinas;
//
//}
