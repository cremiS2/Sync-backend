package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entrada.SetorDTO;
import com.projeto.tcc.dto.pesquisa.SetorResultadoDTO;
import com.projeto.tcc.entities.Setor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FuncionarioMapper.class, MaquinaMapper.class})
public interface SetorMapper  {

    Setor toEntity(SetorDTO dto);

    SetorResultadoDTO toDTO(Setor setor);

}
