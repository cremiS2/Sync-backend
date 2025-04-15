package com.projeto.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.tcc.entities.Maquina;

@Repository
public interface MaquinaRepositoy extends JpaRepository<Maquina, Long>{

}
