package com.projeto.tcc.dto;

import com.projeto.tcc.entities.Usuario;

public record UsuarioDTO(Long id, String username, String email, String senha) {

    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername(), usuario.getEmail(), usuario.getSenha());
    }

}
