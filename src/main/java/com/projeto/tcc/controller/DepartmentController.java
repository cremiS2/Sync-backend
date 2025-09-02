package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.DepartmentDTO;
import com.projeto.tcc.dto.exit.DepartmentResultDTO;
import com.projeto.tcc.dto.mappers.DepartmentMapper;
import com.projeto.tcc.entities.Department;
import com.projeto.tcc.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("departamento")
@RequiredArgsConstructor
public class DepartmentController implements GenericController{

    private final DepartmentService service;
    private final DepartmentMapper mapper;

    @PostMapping
    public ResponseEntity<Void> saveDepartment(@RequestBody @Valid DepartmentDTO dto){
        var entidade = service.saveDepartment(dto);
        return ResponseEntity.created(gerarHeaderLocation(entidade.getId())).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentResultDTO> getById(@PathVariable("id") Long idDepartment){
        return ResponseEntity.ok(service.getDepartmentId(idDepartment));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long idDepartment){
        service.deleteDepartment(idDepartment);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateById(@PathVariable("id") Long idDepartment, @RequestBody DepartmentDTO departmentDTO){
        service.updateDepartment(idDepartment, departmentDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<DepartmentResultDTO>> search(
            @RequestParam(value = "nome-departamento", required = false)
            String name,
//            @RequestParam(value = "local-departamento", required = false)
//            String location,
            @RequestParam(value = "status-departamento", required = false)
            String status,
            @RequestParam(value = "orcamento-departamento", required = false)
            BigDecimal budget,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10")
            Integer pageSize,
            @RequestParam(value = "numero-pagina", required = false, defaultValue = "0")
            Integer pageNumber
    ){

        var department = service.pesquisa(
                name,
                status,
                budget,
                pageNumber,
                pageSize
        );

        return ResponseEntity.ok().body(department);
    }
}
