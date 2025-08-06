package com.projeto.tcc.repository;

import com.projeto.tcc.entities.MachineModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MachineModelRepository extends JpaRepository<MachineModel, Long> {

    Optional<MachineModel> findByNomeModeloAndDescricaoModelo(String nomeModelo, String descricao);
}
