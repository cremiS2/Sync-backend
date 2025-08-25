//package com.projeto.tcc.controller;
//
//import com.projeto.tcc.dto.entry.DepartamentDTO;
//import com.projeto.tcc.service.DepartamentService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("locais")
//@RequiredArgsConstructor
//public class LocalController implements GenericController{
//
//    private final DepartamentService service;
//
//    @PostMapping
//    public ResponseEntity<Void> salvarLocal(@RequestBody @Valid DepartamentDTO dto){
//        var entidade = service.salvarLocal(dto);
//        return ResponseEntity.created(gerarHeaderLocation(entidade.getId())).build();
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<LocalResultadoDTO> verPorId(@PathVariable("id") Long idLocal){
//        return ResponseEntity.ok(service.getLocalId(idLocal));
//    }
//}
