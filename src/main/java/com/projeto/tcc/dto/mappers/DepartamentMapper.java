package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entry.DepartamentDTO;
import com.projeto.tcc.dto.exit.DepartamentResultDTO;
import com.projeto.tcc.entities.Departament;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MachineMapper.class, SectorMapper.class})
public abstract class DepartamentMapper {

    abstract public Departament toEntity(DepartamentDTO dto);

    abstract public DepartamentResultDTO toDTO(Departament departament);
}
