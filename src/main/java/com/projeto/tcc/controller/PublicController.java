package com.projeto.tcc.controller;


import com.projeto.tcc.dto.LoginInformacoes;
import com.projeto.tcc.dto.entry.ForgotPasswordDTO;
import com.projeto.tcc.dto.entry.LoginDTO;
import com.projeto.tcc.dto.entry.ResetPasswordDTO;
import com.projeto.tcc.dto.entry.UserDTO;
import com.projeto.tcc.entities.User;
import com.projeto.tcc.service.TokenService;
import com.projeto.tcc.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PublicController implements GenericController {

    private final TokenService tokenService;
    private final UserService userService;


    @PostMapping("login")
    public ResponseEntity<LoginDTO> login(@RequestBody @Valid LoginInformacoes acces){
        return ResponseEntity.ok().body(tokenService.criarToken(acces));
    }

    @PostMapping("sign-in")
    public ResponseEntity<Void> saveUser(@RequestBody @Valid UserDTO userDTO){
        User user = userService.addUser(userDTO);
        URI uri = gerarHeaderLocation(user.getId());
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("forgot-password")
    public ResponseEntity<Map<String, String>> forgotPassword(@RequestBody @Valid ForgotPasswordDTO forgotPasswordDTO){
        userService.requestPasswordReset(forgotPasswordDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Se o e-mail estiver cadastrado, você receberá instruções para redefinir sua senha.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody @Valid ResetPasswordDTO resetPasswordDTO){
        userService.resetPassword(resetPasswordDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Senha redefinida com sucesso!");
        return ResponseEntity.ok(response);
    }

}
