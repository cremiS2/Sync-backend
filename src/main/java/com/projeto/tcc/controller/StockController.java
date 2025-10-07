package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.StockDTO;
import com.projeto.tcc.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stock")
@RequiredArgsConstructor
public class StockController implements GenericController{

    private final StockService service;


    @GetMapping("{id}")
    public ResponseEntity<StockDTO> getStockById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody @Valid StockDTO dto){
         var uri = gerarHeaderLocation(service.createStock(dto));
         return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<StockDTO>> getPageable(
            @RequestParam(value = "page-size", defaultValue = "10")
            Integer pageSize,
            @RequestParam(value = "page-number", defaultValue = "1")
            Integer pageNumber
    ){
        return ResponseEntity.ok(service.serchStock(pageSize, pageNumber));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateStock(@PathVariable("id") Long id, StockDTO dto){
        service.updateStock(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("id")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        service.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

}
