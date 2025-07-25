package com.projeto.tcc.repository;

import com.projeto.tcc.entities.Endereco;
import com.projeto.tcc.entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalRepository  extends JpaRepository<Local, Long> {

    Optional<Local> findByEnderecoAndNomeUnidadeAndCapacidadeMaquinas(
            Endereco endereco,
            String nomeUnidade,
            Integer capacidadeMaquinas
    );
}
