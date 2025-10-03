package com.projeto.tcc.controller;


import com.projeto.tcc.dto.entry.EmployeeDTO;
import com.projeto.tcc.dto.exit.EmployeeResultDTO;
import com.projeto.tcc.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController implements GenericController {

    private final EmployeeService employeeService;

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> saveEmployee(@RequestBody @Valid EmployeeDTO dto){
        var employee = employeeService.saveEmployee(dto);
        var uri = gerarHeaderLocation(employee.getId());
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_GERENTE')")
    @GetMapping("{id}")
    public ResponseEntity<EmployeeResultDTO> getFuncionarioId(@PathVariable Long id){
        return ResponseEntity.ok().body(employeeService.getFuncionarioId(id));
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateFuncionario(@PathVariable Long id, @RequestBody EmployeeDTO dto){
        employeeService.upadateFuncionario(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_GERENTE')")
    @GetMapping
    public ResponseEntity<Page<EmployeeResultDTO>> pesquisa(
            @RequestParam(value = "employee-name", required = false)
            String name,
            @RequestParam(value = "employee-id", required = false)
            Integer emolyeeID,
            @RequestParam(value = "shift", required = false)
            String shift,
            @RequestParam(value = "sector-name",required = false)
            String nameSector,
            @RequestParam(value = "page-number", defaultValue = "0")
            Integer pageNumber,
            @RequestParam(value = "page-size", defaultValue = "10")
            Integer pageSize
    ){

        var servicePesquisa = employeeService.pesquisa(
                name,
                emolyeeID,
                nameSector,
                shift,
                pageNumber,
                pageSize
        );
        return ResponseEntity.ok(servicePesquisa);
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
