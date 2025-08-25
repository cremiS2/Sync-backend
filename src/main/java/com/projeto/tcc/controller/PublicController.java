package com.projeto.tcc.controller;


import com.projeto.tcc.dto.LoginInformacoes;
import com.projeto.tcc.dto.entry.LoginDTO;
import com.projeto.tcc.dto.entry.UserDTO;
import com.projeto.tcc.service.EmployeeService;
import com.projeto.tcc.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PublicController {

    private final EmployeeService employeeService;
    private final TokenService tokenService;


    @PostMapping("login")
    public ResponseEntity<LoginDTO> entrar(@RequestBody UserDTO acces){
        return ResponseEntity.ok().body(tokenService.criarToken(acces));
    }

}
