package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.UserDTO;
import com.projeto.tcc.dto.mappers.UserMapper;
import com.projeto.tcc.entities.User;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final  Map<Long, User> usuariosCache = new HashMap<>();
    private final UserMapper mapper;

    private User procurarUserCache(Long id) {
        return usuariosCache.computeIfAbsent(id, chave ->
                userRepository.findById(chave).orElse(null)
        );
    }

    @Transactional
    public void adicionarUser(UserDTO usuario) {
        var user = userRepository.findByEmail(usuario.email());

        if(user.isEmpty()){
            var newUsuario = new User();
            newUsuario.setEmail(usuario.email());
            newUsuario.setSenha(passwordEncoder.encode(usuario.senha()));
            userRepository.save(newUsuario);
            usuariosCache.put(newUsuario.getId(), newUsuario);
            System.out.println("Usuario adicionado no cache com sucesso");
        }else{
            throw new NaoRegistradoExcpetion("");
        }
    }


    public UserDTO atualizar(UserDTO user) {
        var usuario = procurarUserCache(user.id());
        if(usuario != null){
            if(user.email() != null && !user.email().equals(usuario.getEmail())){
                usuario.setEmail(user.email());
            }
            if(user.senha() != null && !usuario.verificarSenha(user, passwordEncoder)){
                usuario.setSenha(passwordEncoder.encode(user.senha()));
            }

            userRepository.save(usuario);
            usuariosCache.put(usuario.getId(), usuario);
            System.out.println("Usuario atualizado com sucesso");
            return mapper.toDTO(usuario);

        }else{
            throw new NaoRegistradoExcpetion("Usuário não encontrado");
        }
    }

    public void deletar(Long id) {
        userRepository.deleteById(id);
    }
}
