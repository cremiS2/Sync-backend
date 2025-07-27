package com.projeto.tcc.repository.specs;

import com.projeto.tcc.entities.Funcionario;
import com.projeto.tcc.entities.Role;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class FuncionarioSpecs {

    public static Specification<Funcionario>  nomeLike(String nome){
        return (root, query, cb) -> cb.like(cb.upper(root.get("nome")),"%" + nome.toUpperCase() + "%");
    }

    public static Specification<Funcionario> matriculaEqual(Integer matricula){
        return (root, query, cb) -> cb.equal(root.get("matricula"), matricula);
    }

    public static Specification<Funcionario> escalaEqual(Long idEscala){
        return (root, query, cb) -> {
            Join<Object, Object> joinEscala = root.join("escalaFuncionario", JoinType.INNER);
            return cb.equal(joinEscala.get("id"), idEscala);
        };
    }

    public static Specification<Funcionario> setorLike(String setor){
        return (root, query, cb) -> {
        Join<Object, Object> joinSetor = root.join("setor", JoinType.INNER);
        return cb.like(cb.upper(joinSetor.get("nome")), "%" + setor.toUpperCase() + "%");
        };
    }

    public static Specification<Funcionario> maquinaLike(String maquina){
        return (root, query, cb) -> {
            Join<Object, Object> joinMaquina = root.join("maquinaOperada", JoinType.INNER);
            return cb.like(cb.upper(joinMaquina.get("nomeMaquina")), "%" + maquina.toUpperCase() + "%");
        };
    }

    public static Specification<Funcionario> roleLike(String role){
        return (root, query, cb) -> {
            query.distinct(true);
        Join<Funcionario, Role> joinRole = root.join("roles", JoinType.INNER);
        return cb.like(cb.upper(joinRole.get("name")), "%" + role.toUpperCase() + "%");
        };
    }

}
