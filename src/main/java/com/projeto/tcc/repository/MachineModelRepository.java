package com.projeto.tcc.repository;

import com.projeto.tcc.entities.MachineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MachineModelRepository extends JpaRepository<MachineModel, Long>{

    Optional<MachineModel> findByModelName(String nomeModelo);
}
