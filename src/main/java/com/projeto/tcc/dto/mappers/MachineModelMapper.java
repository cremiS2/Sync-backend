package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entry.MachineModelDTO;
import com.projeto.tcc.dto.exit.MachineModelResultDTO;
import com.projeto.tcc.entities.MachineModel;
import com.projeto.tcc.repository.MachineModelRepository;
import com.projeto.tcc.repository.SectorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = MachineMapper.class)
public interface MachineModelMapper {


    MachineModel toEntity(MachineModelDTO dto);

    @Mapping(target = "machines.machineModel", ignore = true)
    MachineModelResultDTO toDTO(MachineModel entity);

}
