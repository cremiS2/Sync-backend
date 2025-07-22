package com.projeto.tcc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table("")
public class HistoricoAlteracaoEstadosMaquina {
}
