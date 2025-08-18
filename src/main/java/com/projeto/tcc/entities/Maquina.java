package com.projeto.tcc.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
	private String foto_url;
	private LocalDate ultimaManutencao;
	private Double oee;
	private Integer rendimento;

	@ManyToOne
	@JoinColumn(name = "setor_id")
	private Setor setor;

	@ManyToOne
	@JoinColumn(name = "modelo_maquina_id")
	private ModeloMaquinas modeloMaquina;

	@ManyToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamentoMaquina;

	@Enumerated(EnumType.STRING)
	private StatusMaquina status;

	@OneToMany
	private Set<Funcionario> funcionariosOperando;

   //	@ManyToMany(mappedBy = "maquinas", fetch = FetchType.LAZY)
   //	private List<Produto> produtos;

}
