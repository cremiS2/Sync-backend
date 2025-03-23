package com.projeto.tcc.controller;


import com.projeto.tcc.dto.UsuarioDTO;
import com.projeto.tcc.service.CreateUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PublicController {

    private final CreateUserService userService;

    public PublicController(CreateUserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> entrar(@RequestBody UsuarioDTO dto){
        return ResponseEntity.ok().build();
    }

    @PostMapping("registrar")
    public ResponseEntity<?> registrarUser(@RequestBody UsuarioDTO dto){
        userService.adicionarUser(dto);
        return ResponseEntity.ok().build();
    }
}
