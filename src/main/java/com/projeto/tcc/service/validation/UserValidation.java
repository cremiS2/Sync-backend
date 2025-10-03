package com.projeto.tcc.service.validation;

import com.projeto.tcc.dto.entry.UserDTO;
import com.projeto.tcc.entities.Role;
import com.projeto.tcc.entities.User;
import com.projeto.tcc.exceptions.CampoInvalidoException;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.RoleRepository;
import com.projeto.tcc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserValidation {


    private final UserRepository repository;
    private final RoleRepository roleRepository;

    public void validarEntidade(User user, UserDTO dto){
        if(varificarExiste(user)){
            throw new ConflitoCampoException("Usuário com e-mail já cadastrado!");
        }

        //Se colocar mais de uma role, ele não identifica se as outras roles existem!
        if(dto.roles() != null && !dto.roles().isEmpty()){
            Set<Role> rolesUser = roleRepository.findAllByNameIn(dto.roles().stream().map(String::toLowerCase).collect(Collectors.toSet()));
            if (rolesUser.isEmpty()) throw new CampoInvalidoException("roles","Roles não encontradas");
            user.setRoles(rolesUser);
        }
    }


    private boolean varificarExiste(User user) {
        var userFound = repository.findByEmail(user.getEmail());

        if (user.getId() == null) {
            return userFound.isPresent();
        }

        return userFound.isPresent() && user.getId().equals(userFound.get().getId());
    }
}

