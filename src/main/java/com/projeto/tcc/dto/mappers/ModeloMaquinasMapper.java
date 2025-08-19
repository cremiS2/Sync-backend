package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.nao_utilizadas.ModeloMaquinasDTO;
import com.projeto.tcc.dto.pesquisa.ModeloMaquinaResultadoDTO;
import com.projeto.tcc.entities.ModeloMaquinas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MaquinaMapper.class)
public interface ModeloMaquinasMapper {

    @Mapping(target = "maquinas.modeloMaquina", ignore = true)
    ModeloMaquinaResultadoDTO toDTO(ModeloMaquinas entity);

    ModeloMaquinas toEntity(ModeloMaquinasDTO dto);
}
