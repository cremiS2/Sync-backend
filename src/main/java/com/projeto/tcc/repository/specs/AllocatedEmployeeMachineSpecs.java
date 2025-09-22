package com.projeto.tcc.repository.specs;

import com.projeto.tcc.entities.AllocatedEmployeeMachine;
import com.projeto.tcc.entities.Employee;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class AllocatedEmployeeMachineSpecs {

    public static Specification<AllocatedEmployeeMachine> employeeLike(String nameEmployee){
        return (root, query, cb) -> {
            Join<AllocatedEmployeeMachine, Employee> joinEmployee = root.join("employee", JoinType.INNER);
            return cb.like(cb.upper(joinEmployee.get("name")), "%" + nameEmployee.toUpperCase() + "%");
        };
    }
}
