package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entrada.ProdutoDTO;
import com.projeto.tcc.dto.pesquisa.ProdutoResultadoDTO;
import com.projeto.tcc.entities.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper  {

    Produto toEntity(ProdutoDTO dto);
    ProdutoResultadoDTO toDTO(Produto produto);
}
