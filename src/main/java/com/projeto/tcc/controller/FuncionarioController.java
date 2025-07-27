package com.projeto.tcc.controller;


import com.projeto.tcc.dto.entrada.FuncionarioDTO;
import com.projeto.tcc.dto.pesquisa.FuncionarioResultadoDTO;
import com.projeto.tcc.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("funcionarios")
@RequiredArgsConstructor
public class FuncionarioController implements GenericController {

    private final FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Void> salvarFuncionario(@RequestBody @Valid FuncionarioDTO dto){
        var funcionario = funcionarioService.criarFuncionario(dto);
        var uri = gerarHeaderLocation(funcionario.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<FuncionarioResultadoDTO> getFuncionarioId(@PathVariable Long id){
        return ResponseEntity.ok().body(funcionarioService.getFuncionarioId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateFuncionario(@PathVariable Long id, @RequestBody FuncionarioDTO dto){
        funcionarioService.upadateFuncionario(id, dto);
        return ResponseEntity.noContent().build();
    }

}
