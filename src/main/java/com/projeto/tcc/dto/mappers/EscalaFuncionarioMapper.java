package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.nao_utilizadas.EscalaFuncionarioDTO;
import com.projeto.tcc.dto.pesquisa.EscalaFuncionarioResultadoDTO;
import com.projeto.tcc.entities.Turno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = FuncionarioMapper.class)
public interface EscalaFuncionarioMapper {

    Turno toEntity(EscalaFuncionarioDTO dto);

    @Mapping(target = "funcionarios.escalaFuncionario", ignore = true)
    EscalaFuncionarioResultadoDTO toDTO(Turno turno);
}
