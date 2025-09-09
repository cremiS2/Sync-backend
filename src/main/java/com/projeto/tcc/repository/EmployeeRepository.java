package com.projeto.tcc.repository;

import com.projeto.tcc.entities.Employee;
import com.projeto.tcc.entities.Sector;
import com.projeto.tcc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Optional<Employee> findByEmployeeID(Integer employeeID);

    Boolean existsByUser(User user);


}
