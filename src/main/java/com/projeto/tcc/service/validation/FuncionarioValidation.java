package com.projeto.tcc.service.validation;

import com.projeto.tcc.dto.entrada.FuncionarioDTO;
import com.projeto.tcc.entities.Funcionario;
import com.projeto.tcc.entities.Role;
import com.projeto.tcc.exceptions.CampoInvalidoException;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.FuncionarioRepository;
import com.projeto.tcc.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FuncionarioValidation {

    private final FuncionarioRepository repository;
    private final RoleRepository roleRepository;


    public void validarEntidade(Funcionario funcionario, FuncionarioDTO dto){
        if(existeBoolean(funcionario)){
            throw new CampoInvalidoException("matricula", "Funcionário com matrícula já cadastrado!");
        }

        if(funcionario.getId() == null){
            if(funcionario.getEscalaFuncionario() == null){
                throw new CampoInvalidoException("escalaFuncionario","Escala não existente");
            }
            if(funcionario.getSetor() == null){
                throw new CampoInvalidoException("setor","Setor não existente");
            }
        }

       if(dto.roles() != null && !dto.roles().isEmpty()){
           Set<Role> rolesFuncionario = roleRepository.findAllByNameIn(dto.roles().stream().map(String::toLowerCase).collect(Collectors.toSet()));
           if (rolesFuncionario.isEmpty()) throw new CampoInvalidoException("roles","Roles não encontradas");
           funcionario.setRoles(rolesFuncionario);
       }

    }


    private boolean existeBoolean(Funcionario funcionario){
        var procura = repository.findByMatricula(funcionario.getMatricula());
        if(funcionario.getId() == null){
            return procura.isPresent();
        }
        return procura.
                map(p -> !p.getId().equals(funcionario.getId())
                ).orElse(false);
    }

}
