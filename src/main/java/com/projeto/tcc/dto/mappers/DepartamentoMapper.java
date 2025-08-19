package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entrada.DepartamentoDTO;
import com.projeto.tcc.dto.pesquisa.DepartamentoResultadoDTO;
import com.projeto.tcc.entities.Departamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = MaquinaMapper.class)
public abstract class DepartamentoMapper {

    abstract public Departamento toEntity(DepartamentoDTO dto);

    abstract public DepartamentoResultadoDTO toDTO(Departamento departamento);
}
