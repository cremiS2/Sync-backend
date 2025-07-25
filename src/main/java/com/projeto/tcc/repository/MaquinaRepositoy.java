package com.projeto.tcc.repository;

import com.projeto.tcc.entities.Funcionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.projeto.tcc.entities.Maquina;

import java.util.Optional;

@Repository
public interface MaquinaRepositoy extends JpaRepository<Maquina, Long>, JpaSpecificationExecutor<Maquina> {

    Optional<Maquina> findByNumeroSerie(Integer numeroSerie);

    Optional<Maquina> findByFuncionarioOperando(Funcionario funcionario);
}
