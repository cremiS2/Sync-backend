package com.projeto.tcc.repository;

import com.projeto.tcc.entities.ModeloMaquinas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModeloMaquinasRepository  extends JpaRepository<ModeloMaquinas, Long> {

    Optional<ModeloMaquinas> findByNomeModeloAndDescricaoModelo(String nomeModelo, String descricao);
}
