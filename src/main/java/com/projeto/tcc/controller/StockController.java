package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.StockDTO;
import com.projeto.tcc.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stock")
@RequiredArgsConstructor
public class StockController implements GenericController{

    private final StockService service;

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_GERENTE', 'SCOPE_OPERADOR')")
    @GetMapping("{id}")
    public ResponseEntity<StockDTO> getStockById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_GERENTE')")
    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody @Valid StockDTO dto){
         var uri = gerarHeaderLocation(service.createStock(dto));
         return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_GERENTE', 'SCOPE_OPERADOR')")
    @GetMapping
    public ResponseEntity<Page<StockDTO>> getPageable(
            @RequestParam(value = "page-size", defaultValue = "10")
            Integer pageSize,
            @RequestParam(value = "page-number", defaultValue = "1")
            Integer pageNumber
    ){
        return ResponseEntity.ok(service.serchStock(pageSize, pageNumber));
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_GERENTE')")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateStock(@PathVariable("id") Long id, StockDTO dto){
        service.updateStock(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_GERENTE')")
    @DeleteMapping("id")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        service.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

}
