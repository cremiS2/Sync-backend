package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.AllocatedEmployeeMachineDTO;
import com.projeto.tcc.dto.exit.AllocatedEmployeeMachineResultDTO;
import com.projeto.tcc.service.AllocatedEmployeeMachineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("allocated-employee-machine")
@RequiredArgsConstructor
public class AllocatedEmployeeMachineController implements GenericController {

    private final AllocatedEmployeeMachineService service;


    @PostMapping
    public ResponseEntity<Void> saveAllocation(@RequestBody @Valid AllocatedEmployeeMachineDTO dto){
        Long allocationId = service.createAllocatedEmployees(dto);
        URI uri = gerarHeaderLocation(allocationId);
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasHole='admin'")
    @GetMapping
    public ResponseEntity<Page<AllocatedEmployeeMachineResultDTO>> search(
            @RequestParam(name = "page-size", defaultValue = "10")
            Integer pageSize,
            @RequestParam(name = "page-number", defaultValue = "0")
            Integer pageNumber,
            @RequestParam(name = "name-employee", required = false)
            String nameEmployee,
            @RequestParam(name = "name-employee-changed", required = false)
            String nameEmployeeChanged
    ){

        Page<AllocatedEmployeeMachineResultDTO> resultDTOS = service.search(nameEmployee, nameEmployeeChanged,pageSize, pageNumber);
        return ResponseEntity.ok(resultDTOS);
    }
}
