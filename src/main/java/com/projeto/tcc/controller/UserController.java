package com.projeto.tcc.controller;

import com.projeto.tcc.dto.entry.UserDTO;
import com.projeto.tcc.entities.User;
import com.projeto.tcc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController implements GenericController{

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody UserDTO userDTO){
        User user = userService.addUser(userDTO);
        URI uri = gerarHeaderLocation(user.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        userService.update(userDTO, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
