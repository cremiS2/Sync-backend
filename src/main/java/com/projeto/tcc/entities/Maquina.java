package com.projeto.tcc.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_maquina")
public class Maquina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private LocalDate ultima_manutencao;
	
	public Maquina() {
	}

	public Maquina(Long id, String nome, LocalDate ultima_manutencao) {
		this.id = id;
		this.nome = nome;
		this.ultima_manutencao = ultima_manutencao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getUltima_manutencao() {
		return ultima_manutencao;
	}

	public void setUltima_manutencao(LocalDate ultima_manutencao) {
		this.ultima_manutencao = ultima_manutencao;
	}
	
	
	
}
