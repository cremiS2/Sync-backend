package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entrada.LocalDTO;
import com.projeto.tcc.dto.pesquisa.LocalResultadoDTO;
import com.projeto.tcc.entities.Departamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = MaquinaMapper.class)
public abstract class LocalMapper {

    abstract public Departamento toEntity(LocalDTO dto);
    abstract public LocalResultadoDTO toDTO(Departamento departamento);
}
