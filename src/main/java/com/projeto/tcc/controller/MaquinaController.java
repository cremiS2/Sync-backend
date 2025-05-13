package com.projeto.tcc.controller;

import com.projeto.tcc.dto.MaquinaDTO;
import com.projeto.tcc.service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("maquina")
public class MaquinaController {

    @Autowired
    private MaquinaService maquinaService;

    @PostMapping
    public ResponseEntity<Void> cadastrarMaquina(@RequestBody MaquinaDTO maquinaDTO) {
        maquinaService.salvarMaquina(maquinaDTO);
        return ResponseEntity.ok().build();
    }

}
