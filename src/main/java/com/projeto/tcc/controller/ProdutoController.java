package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entrada.ProdutoDTO;
import com.projeto.tcc.dto.pesquisa.ProdutoResultadoDTO;
import com.projeto.tcc.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController implements GenericController{

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid ProdutoDTO dto){
        var entidade = service.salvarProduto(dto);
        return ResponseEntity.created(gerarHeaderLocation(entidade.getId())).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoResultadoDTO> verPorId(@PathVariable("id") Long idProduto){
        return ResponseEntity.ok(service.getProdutoId(idProduto));
    }
}
