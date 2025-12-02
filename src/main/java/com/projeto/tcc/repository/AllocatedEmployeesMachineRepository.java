package com.projeto.tcc.repository;

import com.projeto.tcc.entities.AllocatedEmployeeMachine;
import com.projeto.tcc.entities.Employee;
import com.projeto.tcc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AllocatedEmployeesMachineRepository extends JpaRepository<AllocatedEmployeeMachine, Long>, JpaSpecificationExecutor<AllocatedEmployeeMachine> {

    boolean existsByEmployee(Employee employee);

}
