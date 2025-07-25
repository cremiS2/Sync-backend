package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entrada.MaquinaDTO;
import com.projeto.tcc.dto.mappers.MaquinaMapper;
import com.projeto.tcc.dto.pesquisa.MaquinaResultadoDTO;
import com.projeto.tcc.service.MaquinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("maquinas")
@RequiredArgsConstructor
public class MaquinaController implements GenericController{

    private final MaquinaService maquinaService;
    private final MaquinaMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvarMaquina(@RequestBody @Valid MaquinaDTO maquinaDTO) {
        var entidade = maquinaService.salvarMaquina(maquinaDTO);
        return ResponseEntity.created(gerarHeaderLocation(entidade.getId())).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<MaquinaResultadoDTO> verPorId(@PathVariable("id") Long idMaquina){
        return ResponseEntity.ok(maquinaService.getMaquinaId(idMaquina));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarMaquina(@PathVariable Long id, @RequestBody MaquinaDTO dto){
        System.out.println("ID DA MAQUINA "+ id + " PROPRIEDADE NULA "+ dto.funcionarioOperando());
        maquinaService.updateMaquina(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>  deletarMaquina(@PathVariable Long id){
        maquinaService.deletarMaquina(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<MaquinaResultadoDTO>> pesquisa(
            @RequestParam(value = "nome-maquina", required = false)
            String nomeMaquina,
            @RequestParam(value = "numero-serie", required = false)
            Integer numeroSerie,
            @RequestParam(value = "nome-unidade-local", required = false)
            String nomeUnidadeLocal,
            @RequestParam(value = "nome-modelo", required = false)
            String nomeModelo,
            @RequestParam(value = "nome-setor", required = false)
            String nomeSetor,
            @RequestParam(value = "status-maquina", required = false)
            String statusMaquina,
            @RequestParam(value = "nome-funcionario", required = false)
            String nomeFuncionario,
            @RequestParam(value = "numero-pagina", defaultValue = "0")
            Integer numeroPagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10")
            Integer tamanhoPagina
    ){

        var maquinasPesquisa = maquinaService.pesquisa(
                nomeMaquina,
                numeroSerie,
                nomeUnidadeLocal,
                nomeModelo,
                nomeSetor,
                statusMaquina,
                nomeFuncionario,
                numeroPagina,
                tamanhoPagina
        ).map(mapper::toDTO);

        return ResponseEntity.ok().body(maquinasPesquisa);
    }

}
