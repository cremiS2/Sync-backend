package com.projeto.tcc.repository;

import com.projeto.tcc.entities.AllocatedEmployeeMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AllocatedEmployeesMachineRepository extends JpaRepository<AllocatedEmployeeMachine, Long>, JpaSpecificationExecutor<AllocatedEmployeeMachine> {
}
