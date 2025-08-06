package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.SectorDTO;
import com.projeto.tcc.dto.exit.SectorResultDTO;
import com.projeto.tcc.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("setores")
@RequiredArgsConstructor
public class SetorController implements GenericController{

    private final SectorService sectorService;

    @PostMapping
    public ResponseEntity<URI> salvarSetor(@RequestBody SectorDTO dto){
        var setor = sectorService.criarSetor(dto);
        var uri = gerarHeaderLocation(setor.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<SectorResultDTO> getSetorId(@PathVariable Long id){
        return ResponseEntity.ok().body(sectorService.getSetorId(id));
    }
}
