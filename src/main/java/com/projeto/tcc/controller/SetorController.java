package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entrada.SetorDTO;
import com.projeto.tcc.dto.pesquisa.SetorResultadoDTO;
import com.projeto.tcc.service.SetorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("setores")
@RequiredArgsConstructor
public class SetorController implements GenericController{

    private final SetorService setorService;

    @PostMapping
    public ResponseEntity<URI> salvarSetor(@RequestBody SetorDTO dto){
        var setor = setorService.criarSetor(dto);
        var uri = gerarHeaderLocation(setor.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<SetorResultadoDTO> getSetorId(@PathVariable Long id){
        return ResponseEntity.ok().body(setorService.getSetorId(id));
    }
}
