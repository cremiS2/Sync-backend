package com.projeto.tcc.repository;

import com.projeto.tcc.entities.Employee;
import com.projeto.tcc.entities.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineRepositoy extends JpaRepository<Machine, Long>, JpaSpecificationExecutor<Machine> {

    Optional<Machine> findBySerieNumber(Integer serieNumber);
}
