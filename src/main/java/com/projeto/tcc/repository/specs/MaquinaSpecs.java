package com.projeto.tcc.repository.specs;

import com.projeto.tcc.entities.Maquina;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class MaquinaSpecs {

    public static Specification<Maquina> nomeMaquinaLike(String nomeMaquina){
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("nomeMaquina")), "%" + nomeMaquina.toUpperCase() + "%");
    }

    public static Specification<Maquina> numeroSerieLike(Integer numeroSerie){
        return ((root, query, cb) ->
                cb.equal(root.get("numeroSerie"),  numeroSerie));
    }

    public static Specification<Maquina> localLike(String nomeUnidade){
        return (root, query, cb) ->{
            Join<Object, Object> joinLocal = root.join("localMaquina", JoinType.INNER);
            return cb.like(cb.upper(joinLocal.get("nomeUnidade")), "%" + nomeUnidade.toUpperCase() + "%");

        };
    }

    public static Specification<Maquina> modeloMaquinaLike(String nomeModelo){
        return ((root, query, cb) -> {
            Join joinModelo = root.join("modeloMaquina", JoinType.INNER);
            return cb.like(cb.upper(root.get("nomeModelo")), "%" + nomeModelo.toUpperCase() + "%");
        });
    }

    public static Specification<Maquina> nomeSetorLike(String nomeSetor){
        return ((root, query, cb) -> {
            Join<Object, Object> joinSetor = root.join("setor", JoinType.INNER);
            return cb.like(cb.upper(joinSetor.get("nome")), "%" + nomeSetor.toUpperCase() + "%");
        });
    }

    public static Specification<Maquina> statusMaquinaLike(String statusMaquina){
        return ((root, query, cb) ->
                cb.like(cb.upper(root.get("status")), "%" + statusMaquina.toUpperCase() + "%")
                );
    }

    public static Specification<Maquina> nomeFuncionarioLike(String nomeFuncionario){
        return ((root, query, cb) ->
        {
            Join<Object, Object> joinFuncionario = root.join("funcionarioOperando", JoinType.INNER);
            return cb.like(cb.upper(joinFuncionario.get("nome")), "%" + nomeFuncionario.toUpperCase() + "%");
        }
                );
    }
}
