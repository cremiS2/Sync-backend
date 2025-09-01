package com.projeto.tcc.repository;

import com.projeto.tcc.entities.Department;
import com.projeto.tcc.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long>, JpaSpecificationExecutor<Sector> {

    Optional<Sector> findByNameAndDepartment(String name, Department department);
}
