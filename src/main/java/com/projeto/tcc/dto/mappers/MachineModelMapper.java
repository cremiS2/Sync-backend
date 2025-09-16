package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entry.MachineModelDTO;
import com.projeto.tcc.dto.exit.MachineModelResultDTO;
import com.projeto.tcc.entities.MachineModel;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = MachineMapper.class)
public interface MachineModelMapper {


    MachineModel toEntity(MachineModelDTO dto);

    @Mapping(target = "machines.machineModel", ignore = true)
    MachineModelResultDTO toDTO(MachineModel entity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(MachineModelDTO dto, @MappingTarget MachineModel machineModel);

}
