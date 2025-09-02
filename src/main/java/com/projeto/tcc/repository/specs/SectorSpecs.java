package com.projeto.tcc.repository.specs;

import com.projeto.tcc.entities.Department;
import com.projeto.tcc.entities.Sector;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class SectorSpecs {

    public static Specification<Sector> departmentNameLike(String nameDepartment){
        return (root, query, cb) ->{
            Join<Sector, Department> joinDepartment = root.join("department", JoinType.INNER);
            return cb.like(cb.upper(joinDepartment.get("name")), "%" + nameDepartment.toUpperCase() + "%");
        };
    }

    public static Specification<Sector> sectorNameLike(String sectorName){
        return (root, query, cb) ->
            cb.like(cb.upper(root.get("name")), "%" + sectorName.toUpperCase() + "%");
    }
}
