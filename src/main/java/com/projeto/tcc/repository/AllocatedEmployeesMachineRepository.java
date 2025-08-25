package com.projeto.tcc.repository;

import com.projeto.tcc.entities.AllocatedEmployeeMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocatedEmployeesMachineRepository extends JpaRepository<AllocatedEmployeeMachine, Long> {
}
