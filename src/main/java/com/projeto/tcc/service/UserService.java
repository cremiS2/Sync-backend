package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.UserDTO;
import com.projeto.tcc.dto.exit.UserResultDTO;
import com.projeto.tcc.dto.mappers.UserMapper;
import com.projeto.tcc.entities.User;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.UserRepository;
import com.projeto.tcc.service.validation.UserValidation;
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
    private final UserValidation userValidation;

    private User findUser(Long id) {
        return usuariosCache.computeIfAbsent(id, chave ->
                userRepository.findById(chave).orElse(null)
        );
    }

    @Transactional
    public User addUser(UserDTO usuario) {
        User user1 = mapper.toEntity(usuario);
        user1.setPassword(passwordEncoder.encode(usuario.password()));
        userValidation.validarEntidade(user1);
        user1 = userRepository.save(user1);
        usuariosCache.put(user1.getId(), user1);

        return user1;
    }


    @Transactional
    public void update(UserDTO userDTO, Long id) {
        User user = findUser(id);
        mapper.updateEntidade(user, userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        userRepository.save(user);
        usuariosCache.put(user.getId(), user);
        System.out.println("Usuario atualizado com sucesso");
    }

    public void delete(Long id){
        var user  = findUser(id);
        if(user != null){
            userRepository.delete(user);
        }else{
            throw new NaoRegistradoException("User com o id " + id + " não encontrado");
        }
    }
    }



