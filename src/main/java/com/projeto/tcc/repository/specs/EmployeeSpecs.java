package com.projeto.tcc.repository.specs;

import com.projeto.tcc.entities.Employee;
import com.projeto.tcc.entities.Role;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecs {

    public static Specification<Employee>  nomeLike(String nome){
        return (root, query, cb) -> cb.like(cb.upper(root.get("nome")),"%" + nome.toUpperCase() + "%");
    }

    public static Specification<Employee> matriculaEqual(Integer matricula){
        return (root, query, cb) -> cb.equal(root.get("matricula"), matricula);
    }

    public static Specification<Employee> escalaEqual(Long idEscala){
        return (root, query, cb) -> {
            Join<Object, Object> joinEscala = root.join("escalaFuncionario", JoinType.INNER);
            return cb.equal(joinEscala.get("id"), idEscala);
        };
    }

    public static Specification<Employee> setorLike(String setor){
        return (root, query, cb) -> {
        Join<Object, Object> joinSetor = root.join("setor", JoinType.INNER);
        return cb.like(cb.upper(joinSetor.get("nome")), "%" + setor.toUpperCase() + "%");
        };
    }

    public static Specification<Employee> maquinaLike(String maquina){
        return (root, query, cb) -> {
            Join<Object, Object> joinMaquina = root.join("maquinaOperada", JoinType.INNER);
            return cb.like(cb.upper(joinMaquina.get("nomeMaquina")), "%" + maquina.toUpperCase() + "%");
        };
    }

    public static Specification<Employee> roleLike(String role){
        return (root, query, cb) -> {
            query.distinct(true);
        Join<Employee, Role> joinRole = root.join("roles", JoinType.INNER);
        return cb.like(cb.upper(joinRole.get("name")), "%" + role.toUpperCase() + "%");
        };
    }

}
