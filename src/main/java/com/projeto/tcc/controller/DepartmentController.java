package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.DepartmentDTO;
import com.projeto.tcc.dto.exit.DepartmentResultDTO;
import com.projeto.tcc.dto.mappers.DepartmentMapper;
import com.projeto.tcc.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("department")
@RequiredArgsConstructor
public class DepartmentController implements GenericController{

    private final DepartmentService service;
    private final DepartmentMapper mapper;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> saveDepartment(@RequestBody @Valid DepartmentDTO dto){
        var entidade = service.saveDepartment(dto);
        return ResponseEntity.created(gerarHeaderLocation(entidade.getId())).build();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_GERENTE', 'SCOPE_OPERADOR')")
    public ResponseEntity<DepartmentResultDTO> getById(@PathVariable("id") Long idDepartment){
        return ResponseEntity.ok(service.getDepartmentId(idDepartment));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long idDepartment){
        service.deleteDepartment(idDepartment);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> updateById(@PathVariable("id") Long idDepartment, @RequestBody DepartmentDTO departmentDTO){
        service.updateDepartment(idDepartment, departmentDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_GERENTE', 'SCOPE_OPERADOR')")
    public ResponseEntity<Page<DepartmentResultDTO>> search(
            @RequestParam(value = "department-name", required = false)
            String name,
//            @RequestParam(value = "local-departamento", required = false)
//            String location,
            @RequestParam(value = "status-department", required = false)
            String status,
            @RequestParam(value = "department-budget", required = false)
            BigDecimal budget,
            @RequestParam(value = "page-size", defaultValue = "10")
            Integer pageSize,
            @RequestParam(value = "page-number", required = false, defaultValue = "0")
            Integer pageNumber
    ){

        var department = service.search(
                name,
                status,
                budget,
                pageNumber,
                pageSize
        );

        return ResponseEntity.ok().body(department);
    }
}
