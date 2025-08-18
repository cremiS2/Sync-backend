package com.projeto.tcc.repository;

import com.projeto.tcc.entities.Endereco;
import com.projeto.tcc.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalRepository  extends JpaRepository<Departamento, Long> {

    Optional<Departamento> findByEnderecoAndNomeUnidadeAndCapacidadeMaquinas(
            Endereco endereco,
            String nomeUnidade,
            Integer capacidadeMaquinas
    );
}
