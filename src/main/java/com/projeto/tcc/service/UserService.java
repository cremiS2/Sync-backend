package com.projeto.tcc.service;

import com.projeto.tcc.dto.entrada.UsuarioDTO;
import com.projeto.tcc.dto.mappers.UserMapper;
import com.projeto.tcc.entities.Usuario;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final  Map<Long, Usuario> usuariosCache = new HashMap<>();
    private final UserMapper mapper;

    private Usuario procurarUserCache(Long id) {
        return usuariosCache.computeIfAbsent(id, chave ->
                usuarioRepository.findById(chave).orElse(null)
        );
    }

    @Transactional
    public void adicionarUser(UsuarioDTO usuario) {
        var user = usuarioRepository.findByEmail(usuario.email());

        if(user.isEmpty()){
            var newUsuario = new Usuario();
            newUsuario.setEmail(usuario.email());
            newUsuario.setSenha(passwordEncoder.encode(usuario.senha()));
            usuarioRepository.save(newUsuario);
            usuariosCache.put(newUsuario.getId(), newUsuario);
            System.out.println("Usuario adicionado no cache com sucesso");
        }else{
            throw new NaoRegistradoExcpetion("");
        }
    }


    public UsuarioDTO atualizar(UsuarioDTO user) {
        var usuario = procurarUserCache(user.id());
        if(usuario != null){
            if(user.email() != null && !user.email().equals(usuario.getEmail())){
                usuario.setEmail(user.email());
            }
            if(user.senha() != null && !usuario.verificarSenha(user, passwordEncoder)){
                usuario.setSenha(passwordEncoder.encode(user.senha()));
            }

            usuarioRepository.save(usuario);
            usuariosCache.put(usuario.getId(), usuario);
            System.out.println("Usuario atualizado com sucesso");
            return mapper.toDTO(usuario);

        }else{
            throw new NaoRegistradoExcpetion("Usuário não encontrado");
        }
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
