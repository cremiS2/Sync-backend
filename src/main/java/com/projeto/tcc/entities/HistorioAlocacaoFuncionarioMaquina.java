package com.projeto.tcc.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "tb_alocacao_func_maquina")
@Data
@EntityListeners(AuditingEntityListener.class)
public class HistorioAlocacaoFuncionarioMaquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @OneToOne
    @JoinColumn(name = "maquina_origem")
    private Maquina maquinaOrigem;

    @OneToOne
    @JoinColumn(name = "maquina_destino")
    private Maquina maquinaDestino;

    @CreatedDate
    @Column(name = "data_alocacao")
    private LocalDate dataAlocacao;

    @OneToOne
    @JoinColumn(name = "funcionario_alterou")
    private Funcionario funcionarioAlterou;
}
