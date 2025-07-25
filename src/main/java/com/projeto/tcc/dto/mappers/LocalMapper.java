package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entrada.LocalDTO;
import com.projeto.tcc.dto.pesquisa.LocalResultadoDTO;
import com.projeto.tcc.entities.Local;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = MaquinaMapper.class)
public abstract class LocalMapper {

    abstract public Local toEntity(LocalDTO dto);
    abstract public LocalResultadoDTO toDTO(Local local);
}
