package com.projeto.tcc.repository;

import com.projeto.tcc.entities.Departament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentRepository extends JpaRepository<Departament, Long> {
//
//    Optional<Departament> findByEnderecoAndNomeUnidadeAndCapacidadeMaquinas(
//            String nomeUnidade,
//            Integer capacidadeMaquinas
//    );
}
