package com.projeto.tcc.entities;

import com.projeto.tcc.enums.StatusMachine;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tb_alteracao_estado_maquina")
public class HistoricoAlteracaoEstadoMaquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "maquina_alterada")
    private Machine machine;

    @Column(name = "status_antigo")
    @Enumerated(EnumType.STRING)
    private StatusMachine statusAntigo;

    @Column(name = "status_novo")
    @Enumerated(EnumType.STRING)
    private StatusMachine statusNovo;

    @CreatedDate
    @Column(name = "data_alteracao")
    private LocalDate dataAlteracao;

    @OneToOne
    @JoinColumn(name = "funcionario_alterou")
    private Employee employee;


}
