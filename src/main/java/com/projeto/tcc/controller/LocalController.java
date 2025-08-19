package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entrada.DepartamentoDTO;
import com.projeto.tcc.dto.pesquisa.LocalResultadoDTO;
import com.projeto.tcc.service.DepartamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("locais")
@RequiredArgsConstructor
public class LocalController implements GenericController{

    private final DepartamentoService service;

    @PostMapping
    public ResponseEntity<Void> salvarLocal(@RequestBody @Valid DepartamentoDTO dto){
        var entidade = service.salvarLocal(dto);
        return ResponseEntity.created(gerarHeaderLocation(entidade.getId())).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<LocalResultadoDTO> verPorId(@PathVariable("id") Long idLocal){
        return ResponseEntity.ok(service.getLocalId(idLocal));
    }
}
