package com.projeto.tcc.controller;


import com.projeto.tcc.dto.LoginDTO;
import com.projeto.tcc.dto.UsuarioDTO;
import com.projeto.tcc.service.UserService;
import com.projeto.tcc.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PublicController {

    private final UserService userService;
    private final TokenService tokenService;

    public PublicController(UserService userService, TokenService serviceToken) {
        this.userService = userService;
        this.tokenService = serviceToken;
    }

    @PostMapping("login")
    public ResponseEntity<LoginDTO> entrar(@RequestBody UsuarioDTO acces){
        return ResponseEntity.ok().body(tokenService.criarToken(acces));
    }

    @PostMapping("registrar")
    public ResponseEntity<?> registrarUser(@RequestBody UsuarioDTO dto){
        try{
            userService.adicionarUser(dto);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }
}
