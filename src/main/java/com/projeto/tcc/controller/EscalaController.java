package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entrada.EscalaFuncionarioDTO;
import com.projeto.tcc.dto.pesquisa.EscalaFuncionarioResultadoDTO;
import com.projeto.tcc.service.EscalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("escalas")
@RequiredArgsConstructor
public class EscalaController implements GenericController{

    private final EscalaService escalaService;


    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<URI> salvarEscala(@RequestBody @Valid EscalaFuncionarioDTO dto){
        var escala = escalaService.criarEscalaFuncionario(dto);
        var uri = gerarHeaderLocation(escala.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<EscalaFuncionarioResultadoDTO> verPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(escalaService.getEscalaId(id));
    }



}
