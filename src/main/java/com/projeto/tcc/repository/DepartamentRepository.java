package com.projeto.tcc.repository;

import com.projeto.tcc.entities.Departament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartamentRepository extends JpaRepository<Departament, Long> {

    Optional<Departament> findByNameAndLocation(String name, String location);
}
