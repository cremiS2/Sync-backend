package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.SectorDTO;
import com.projeto.tcc.dto.exit.SectorResultDTO;
import com.projeto.tcc.service.SectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("setor")
@RequiredArgsConstructor
public class SectorController implements GenericController{

    private final SectorService sectorService;

    @PostMapping
    public ResponseEntity<URI> salvarSetor(@RequestBody @Valid SectorDTO dto){
        var setor = sectorService.createSector(dto);
        var uri = gerarHeaderLocation(setor.getId());
        return ResponseEntity.created(uri).build();
    }


    @GetMapping("{id}")
    public ResponseEntity<SectorResultDTO> getSetorId(@PathVariable Long id){
        return ResponseEntity.ok().body(sectorService.getSectorId(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSector(@PathVariable Long id){
        sectorService.deleteSector(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<SectorResultDTO>> research(
            @RequestParam(value = "nome-departamento", required = false)
            String departmentName,
            @RequestParam(value = "nome-setor", required = false)
            String sectorName,
            @RequestParam(value = "numero-pagina", defaultValue = "0")
            Integer pageNumber,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10")
            Integer pageSize
    ){
        return ResponseEntity.ok().body(
                sectorService.pesquisa(
                        departmentName,
                        sectorName,
                        pageSize,
                        pageNumber
                )
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody SectorDTO sectorDTO){
        sectorService.updateSector(id, sectorDTO);
        return ResponseEntity.noContent().build();
    }
}
