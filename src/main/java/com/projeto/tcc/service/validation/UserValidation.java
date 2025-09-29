package com.projeto.tcc.service.validation;

import com.projeto.tcc.entities.User;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidation {

    @Autowired
    private UserRepository repository;

    public void validarEntidade(User user){
        if(varificarExiste(user)){
            throw new ConflitoCampoException("Usuário com e-mail já cadastrado!");
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

