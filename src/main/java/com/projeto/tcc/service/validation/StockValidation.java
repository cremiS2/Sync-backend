package com.projeto.tcc.service.validation;

import com.projeto.tcc.entities.Stock;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockValidation {

    private final StockRepository repository;

    public void entityValidation(Stock stock){
        if(existStock(stock)){
            throw new ConflitoCampoException("Estoque não encontrado!");
        }
    }


    private boolean existStock(Stock stock){
        var existe = repository.findByCodigo(stock.getCodigo());

        if(stock.getId() == null){
            return existe.isPresent();
        }

        return existe.isPresent() && !stock.getId().equals(existe.get().getId());
    }
}
