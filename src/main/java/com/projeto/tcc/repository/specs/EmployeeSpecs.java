package com.projeto.tcc.repository.specs;

import com.projeto.tcc.entities.Employee;
import com.projeto.tcc.entities.Role;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecs {

    public static Specification<Employee> nameLike(String nome){
        return (root, query, cb) -> cb.like(cb.upper(root.get("name")),"%" + nome.toUpperCase() + "%");
    }

    public static Specification<Employee> employeeEqual(Integer employeeID){
        return (root, query, cb) -> cb.equal(root.get("employeeID"), employeeID);
    }


    public static Specification<Employee> sectorLike(String sector){
        return (root, query, cb) -> {
        Join<Object, Object> joinSetor = root.join("sector", JoinType.INNER);
        return cb.like(cb.upper(joinSetor.get("name")), "%" + sector.toUpperCase() + "%");
        };
    }

    public static Specification<Employee> shiftEqual(String nameShift){
        return (root, query, cb) ->
            cb.like(cb.upper(root.get("shift")), "%" + nameShift.toUpperCase() + "%");
    }



}
