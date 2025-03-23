package com.projeto.tcc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.tcc.entities.Usuario;
import com.projeto.tcc.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario adicionar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizar(Long id, Usuario usuario) {
        Usuario usuarioExistente = buscarPorId(id);
        if (usuarioExistente != null) {
            usuarioExistente.setUsername(usuario.getUsername());
            usuarioExistente.setSenha(usuario.getSenha());
            usuarioExistente.setEmail(usuario.getEmail());
            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
