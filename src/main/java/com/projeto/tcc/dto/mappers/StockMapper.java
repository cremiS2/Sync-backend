package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entry.StockDTO;
import com.projeto.tcc.entities.Stock;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface StockMapper {

    Stock toEntity(StockDTO dto);
    StockDTO toDTO(Stock entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Stock stock, StockDTO dto);
}
