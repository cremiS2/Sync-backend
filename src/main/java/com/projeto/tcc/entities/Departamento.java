package com.projeto.tcc.entities;

import com.projeto.tcc.enuns.StatusDepartamento;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tb_departamento")
@Data
@EnableJpaAuditing()
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departamento_id")
    private Long id;

    private String nomeDepartamento;

    private String descricao;

    private String localizacao;

    @Enumerated(EnumType.STRING)
    private StatusDepartamento statusDepartamento;


    @CreatedDate
    private LocalDateTime dataCriacao;

    @OneToMany(mappedBy = "departamento")
    private Set<Funcionario> funcionarios;

    private BigDecimal orcamento;

    @OneToMany(mappedBy = "departamento")
    private Set<Setor> setores;

    //    @Embedded
    //    private Endereco endereco;

}
