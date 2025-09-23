package com.projeto.tcc.repository.specs;

import com.projeto.tcc.entities.Department;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class DepartmentSpecs {

    public static Specification<Department> nameLike(String name) {
        if (name == null || name.trim().isEmpty()) {
            return (root, query, cb) -> cb.conjunction(); // Retorna uma condição vazia
        }
        String nameUpper = name.trim().toUpperCase(); // Evita problemas com espaços em branco
        return (root, query, cb) -> cb.like(cb.upper(root.get("name")), "%" + nameUpper + "%");
    }


//    public static Specification<Department> locationLike(String location){
//        return (root, query, cb) ->
//                cb.like(cb.upper(root.get("location")), "%" + location + "%");
//    }

    public static Specification<Department> statusLike(String status){
        return (root, query, cb) ->
                cb.equal(cb.upper(root.get("status")),  status.toUpperCase());
    }

    public static Specification<Department> budgetEqual(BigDecimal budget){
        return (root, query, cb) ->
                cb.equal(root.get("budget"), budget);
    }
}
