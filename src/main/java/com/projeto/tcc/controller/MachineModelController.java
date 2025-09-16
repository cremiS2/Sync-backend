package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.MachineModelDTO;
import com.projeto.tcc.dto.exit.MachineModelResultDTO;
import com.projeto.tcc.service.MachineModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("machine-model")
@RequiredArgsConstructor
public class MachineModelController implements GenericController{

    private final MachineModelService service;

    @PostMapping
    public ResponseEntity<Void> saveMachineModel(@RequestBody @Valid MachineModelDTO dto){
        var entidade = service.saveModel(dto);
        return ResponseEntity.created(gerarHeaderLocation(entidade.getId())).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<MachineModelResultDTO> getById(@PathVariable("id") Long idModelo){
        return ResponseEntity.ok(service.getModeloMaquinaId(idModelo));
    }

    @GetMapping
    public ResponseEntity<Page<MachineModelResultDTO>> getAllModels(
            @RequestParam(name = "numero-pagina", defaultValue = "0")
            Integer pageNumber,
            @RequestParam(name = "tamanho-pagina", defaultValue = "10")
            Integer pageSize
    ){
        Page<MachineModelResultDTO> getAllModelsService = service.getAllModels(pageSize, pageNumber);
        return ResponseEntity.ok(getAllModelsService);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateEntity(@PathVariable("id") Long id, @RequestBody MachineModelDTO machineModelDTO){
        service.updateMachineModel(id, machineModelDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable("id") Long id){
        service.deleteMachineModel(id);
        return ResponseEntity.noContent().build();
    }


}
