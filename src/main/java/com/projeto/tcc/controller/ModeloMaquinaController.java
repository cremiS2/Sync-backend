package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entrada.ModeloMaquinasDTO;
import com.projeto.tcc.dto.pesquisa.ModeloMaquinaResultadoDTO;
import com.projeto.tcc.service.ModeloMaquinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("modelo-maquinas")
@RequiredArgsConstructor
public class ModeloMaquinaController implements GenericController{

    private final ModeloMaquinaService service;

    @PostMapping
    public ResponseEntity<Void> salvarModeloMaquina(@RequestBody @Valid ModeloMaquinasDTO dto){
        var entidade = service.salvarModelo(dto);
        return ResponseEntity.created(gerarHeaderLocation(entidade.getId())).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ModeloMaquinaResultadoDTO> verPorId(@PathVariable("id") Long idModelo){
        return ResponseEntity.ok(service.getModeloMaquinaId(idModelo));
    }


}
