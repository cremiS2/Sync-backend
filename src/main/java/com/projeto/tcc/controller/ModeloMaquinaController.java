package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.MachineModelDTO;
import com.projeto.tcc.dto.exit.MachineModelResultDTO;
import com.projeto.tcc.service.MachineModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("modelo-maquinas")
@RequiredArgsConstructor
public class ModeloMaquinaController implements GenericController{

    private final MachineModelService service;

    @PostMapping
    public ResponseEntity<Void> salvarModeloMaquina(@RequestBody @Valid MachineModelDTO dto){
        var entidade = service.saveModel(dto);
        return ResponseEntity.created(gerarHeaderLocation(entidade.getId())).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<MachineModelResultDTO> verPorId(@PathVariable("id") Long idModelo){
        return ResponseEntity.ok(service.getModeloMaquinaId(idModelo));
    }


}
