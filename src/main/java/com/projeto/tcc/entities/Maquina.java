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

	private String name;
	private String photo;
	private LocalDate lastMaintenance;
	private Float oee;
	private Integer throughput;
	private Integer serieNumber;

	@ManyToOne
	@JoinColumn(name = "setor_id")
	private Setor sector;

	@ManyToOne
	@JoinColumn(name = "modelo_maquina_id")
	private ModeloMaquinas modelMachine;

	@Enumerated(EnumType.STRING)
	private StatusMaquina status;

	@OneToMany
	private Set<Funcionario> employees;

}
