package com.projeto.tcc.repository.specs;

import com.projeto.tcc.entities.Machine;
import com.projeto.tcc.entities.Sector;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class MachineSpecs {

    public static Specification<Machine> nameLike(String nameMachine){
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("name")), "%" + nameMachine.toUpperCase() + "%");
    }

    public static Specification<Machine> sectorLike(String sector){
        return (root, query, cb) ->{
            Join<Machine, Sector> joinSector = root.join("sector", JoinType.INNER);
            return cb.like(cb.upper(joinSector.get("name")), "%" + sector.toUpperCase() + "%");

        };
    }

    public static Specification<Machine> statusMachineLike(String statusMachine){
        return ((root, query, cb) ->
                cb.like(cb.upper(root.get("status")), "%" + statusMachine.toUpperCase() + "%")
                );
    }

}
