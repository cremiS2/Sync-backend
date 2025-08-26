package com.projeto.tcc.entities;

import java.time.LocalDate;

import com.projeto.tcc.enums.StatusMachine;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="tb_machine")
@Data
public class Machine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "machine_id")
	private Long id;

	private String name;
	private String photo;
	private LocalDate lastMaintenance;
	private Float oee;
	private Integer throughput;
	private Integer serieNumber;

	@ManyToOne
	@JoinColumn(name = "sector_id")
	private Sector sector;

	@ManyToOne
	@JoinColumn(name = "machine_model_id")
	private MachineModel machineModel;

	@Enumerated(EnumType.STRING)
	private StatusMachine status;

	@OneToOne(mappedBy = "machine")
	private AllocatedEmployeeMachine allocatedEmployeeMachine;

}
