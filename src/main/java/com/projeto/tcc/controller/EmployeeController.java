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

//    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> saveEmployee(@RequestBody @Valid EmployeeDTO dto){
        var employee = employeeService.criarFuncionario(dto);
        var uri = gerarHeaderLocation(employee.getId());
        return ResponseEntity.created(uri).build();
    }

//    @PreAuthorize("hasAnyAuthority('SCOPE_GERENTE', 'SCOPE_ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<EmployeeResultDTO> getFuncionarioId(@PathVariable Long id){
        return ResponseEntity.ok().body(employeeService.getFuncionarioId(id));
    }

//    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateFuncionario(@PathVariable Long id, @RequestBody EmployeeDTO dto){
        employeeService.upadateFuncionario(id, dto);
        return ResponseEntity.noContent().build();
    }

//    @PreAuthorize("hasAnyAuthority('SCOPE_GERENTE', 'SCOPE_ADMIN')")
    @GetMapping
    public ResponseEntity<Page<EmployeeResultDTO>> pesquisa(
            @RequestParam(value = "nome-funcionario", required = false)
            String nome,
            @RequestParam(value = "matricula-funcionario", required = false)
            Integer emolyeeID,
            @RequestParam(value = "nome-turno", required = false)
            String shift,
            @RequestParam(value = "nome-setor",required = false)
            String nomeSetor,
            @RequestParam(value = "numero-pagina", defaultValue = "0")
            Integer numeroPagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10")
            Integer tamanhoPagina
    ){

        var servicePesquisa = employeeService.pesquisa(
                nome,
                emolyeeID,
                nomeSetor,
                shift,
                numeroPagina,
                tamanhoPagina
        );
        return ResponseEntity.ok(servicePesquisa);
    }


    @DeleteMapping("{id}")
    public void deletarFuncionario(@PathVariable Long id){
        employeeService.deletarFuncionario(id);
    }

}
