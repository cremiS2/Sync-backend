package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entry.MachineDTO;
import com.projeto.tcc.dto.exit.MachineResultDTO;
import com.projeto.tcc.entities.Machine;
import com.projeto.tcc.repository.EmployeeRepository;
import com.projeto.tcc.repository.MachineModelRepository;
import com.projeto.tcc.repository.SectorRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {MachineModelMapper.class, SectorMapper.class, AllocatedEmployeeMachineMapper.class})
public abstract class MachineMapper {

    @Autowired
    SectorRepository sectorRepository;
    @Autowired
    MachineModelRepository machineModelRepository;

    //CREATE

    @Mapping(target = "sector",
            expression =
                    "java(dto.sector() == null ? null : sectorRepository.findById(dto.sector()).orElse(null))")

    @Mapping(target = "machineModel",
            expression =
                    "java(dto.machineModel() == null ? null : machineModelRepository.findById(dto.machineModel()).orElse(null))")
    @Mapping(target = "status", ignore = true)
    abstract public Machine toEntity(MachineDTO dto);


    // TRANSFORMAÇÃO

    @Mapping(target = "allocatedEmployeesMachine.machine", ignore = true)
    abstract public MachineResultDTO toDTO(Machine machine);


    //UPDATE
    @Mapping(target = "sector",
            expression =
                    "java(dto.sector() == null ? machine.getSector() : sectorRepository.findById(dto.sector()).orElse(null))")

    @Mapping(target = "machineModel",
            expression =
                    "java(dto.machineModel() == null ? null : machineModelRepository.findById(dto.machineModel()).orElse(null))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "status", ignore = true)
    public abstract void updateEntityFromDto(MachineDTO dto, @MappingTarget Machine machine);





}
