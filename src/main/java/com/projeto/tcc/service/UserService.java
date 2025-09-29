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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public User findUser(Long id) {
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
        if(userDTO.password() != null){
            user.setPassword(passwordEncoder.encode(userDTO.password()));
        }
        userRepository.save(user);
        usuariosCache.put(user.getId(), user);
        System.out.println("Usuario atualizado com sucesso");
    }

    @Transactional
    public void delete(Long id){
        var user  = findUser(id);
        if(user != null){
            userRepository.delete(user);
            usuariosCache.remove(id);
        }else{
            throw new NaoRegistradoException("User com o id " + id + " não encontrado");
        }
    }

    public UserResultDTO getById(Long id){
        return userRepository.findById(id).map(
                mapper::toDTO
        ).orElseThrow(() -> new NaoRegistradoException("user não encontrado"));
    }

    public Page<UserResultDTO> getPaged(Integer numberPage, Integer pageSize){
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return userRepository.findAll(pageable).map(mapper::toDTO);
    }
}



