package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.AllocatedEmployeeMachineDTO;
import com.projeto.tcc.service.AllocatedEmployeeMachineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
