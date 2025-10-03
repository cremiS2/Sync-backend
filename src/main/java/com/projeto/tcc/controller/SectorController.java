package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.SectorDTO;
import com.projeto.tcc.dto.exit.SectorResultDTO;
import com.projeto.tcc.service.SectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("sector")
@RequiredArgsConstructor
public class SectorController implements GenericController{

    private final SectorService sectorService;

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<URI> saveSector(@RequestBody @Valid SectorDTO dto){
        var sectorId = sectorService.createSector(dto);
        var uri = gerarHeaderLocation(sectorId);
        return ResponseEntity.created(uri).build();
    }


    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_GERENTE')")
    @GetMapping("{id}")
    public ResponseEntity<SectorResultDTO> getSectorId(@PathVariable Long id){
        return ResponseEntity.ok().body(sectorService.getSectorId(id));
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSector(@PathVariable Long id){
        sectorService.deleteSector(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_GERENTE')")
    @GetMapping
    public ResponseEntity<Page<SectorResultDTO>> research(
            @RequestParam(value = "department-name", required = false)
            String departmentName,
            @RequestParam(value = "sector-name", required = false)
            String sectorName,
            @RequestParam(value = "page-size", defaultValue = "0")
            Integer pageNumber,
            @RequestParam(value = "page-number", defaultValue = "10")
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

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody SectorDTO sectorDTO){
        sectorService.updateSector(id, sectorDTO);
        return ResponseEntity.noContent().build();
    }
}
