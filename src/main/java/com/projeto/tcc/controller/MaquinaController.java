package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.MachineDTO;
import com.projeto.tcc.dto.mappers.MachineMapper;
import com.projeto.tcc.dto.exit.MachineResultDTO;
import com.projeto.tcc.service.MachineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("maquinas")
@RequiredArgsConstructor
public class MaquinaController implements GenericController{

    private final MachineService machineService;
    private final MachineMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvarMaquina(@RequestBody @Valid MachineDTO machineDTO) {
        var entidade = machineService.salvarMaquina(machineDTO);
        return ResponseEntity.created(gerarHeaderLocation(entidade.getId())).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<MachineResultDTO> verPorId(@PathVariable("id") Long idMaquina){
        return ResponseEntity.ok(machineService.getMaquinaId(idMaquina));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarMaquina(@PathVariable Long id, @RequestBody MachineDTO dto){
        machineService.updateMaquina(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>  deletarMaquina(@PathVariable Long id){
        machineService.deletarMaquina(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<MachineResultDTO>> pesquisa(
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

        var maquinasPesquisa = machineService.pesquisa(
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
