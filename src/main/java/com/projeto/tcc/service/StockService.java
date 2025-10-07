package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.StockDTO;
import com.projeto.tcc.dto.mappers.StockMapper;
import com.projeto.tcc.entities.Stock;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.StockRepository;
import com.projeto.tcc.service.validation.StockValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository repository;
    private final StockMapper mapper;
    private final StockValidation validation;

    public Long createStock(StockDTO dto){
        Stock stock = mapper.toEntity(dto);
        validation.entityValidation(stock);
        return repository.save(stock).getId();
    }

    public StockDTO getById(Long id){
        return mapper.toDTO(repository.findById(id).orElseThrow(
                () -> new NaoRegistradoException("Stock com id " + id + " não encontrado")));
    }

    public Page<StockDTO> serchStock(
            Integer pageSize,
            Integer pageNumber
    ){

        Pageable page = PageRequest.of(pageNumber, pageSize);

        Page<Stock> pageStock = repository.findAll(page);

        return pageStock.map(mapper::toDTO);
    }

    public void deleteStock(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new NaoRegistradoException("Estoque não encontrado");
        }
    }

    public void updateStock(Long id, StockDTO dto){
        validation.entityValidation(mapper.toEntity(dto));
        Stock stock = repository.findById(id).orElseThrow(() -> new NaoRegistradoException("Stock não encontrado!"));
        mapper.update(stock, dto);
        repository.save(stock);
    }

}
