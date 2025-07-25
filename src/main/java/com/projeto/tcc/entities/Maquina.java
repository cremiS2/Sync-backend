package com.projeto.tcc.entities;

import java.time.LocalDate;
import java.util.List;

import com.projeto.tcc.enuns.StatusMaquina;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="tb_maquina")
@Data
public class Maquina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maquina_id")
	private Long id;

	private String nomeMaquina;

	private Integer numeroSerie;

	@ManyToOne
	@JoinColumn(name = "setor_id")
	private Setor setor;


	@ManyToOne
	@JoinColumn(name = "modelo_maquina_id")
	private ModeloMaquinas modeloMaquina;

	@ManyToOne
	@JoinColumn(name = "local_id")
	private Local localMaquina;

	private LocalDate ultimaManutencao;

	@Enumerated(EnumType.STRING)
	private StatusMaquina status;

	@OneToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionarioOperando;

	@ManyToMany(mappedBy = "maquinas", fetch = FetchType.LAZY)
	private List<Produto> produtos;
}
