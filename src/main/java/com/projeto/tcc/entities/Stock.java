package com.projeto.tcc.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tb_stock")
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock")
    Long id;

    private String codigo;

    private String nome;

    private String categoria;

    private Integer quantidade;

    private String unidade;

    private BigDecimal precoUnitario;

    private String fornecedor;

    private Date dataEntrada;

    private Date dataValidade;

    private String localizacao;

    private String status;

    private String descricao;
}
