package com.projeto.tcc.service;

import com.projeto.tcc.dto.entrada.FuncionarioDTO;
import com.projeto.tcc.dto.mappers.FuncionarioMapper;
import com.projeto.tcc.dto.pesquisa.FuncionarioResultadoDTO;
import com.projeto.tcc.entities.Funcionario;
import com.projeto.tcc.entities.Role;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.FuncionarioRepository;
import com.projeto.tcc.repository.RoleRepository;
import com.projeto.tcc.service.validation.FuncionarioValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final FuncionarioMapper mapper;
    private final RoleRepository roleRepository;
    private final FuncionarioValidation validation;

    public Funcionario criarFuncionario(FuncionarioDTO dto){
        Funcionario funcionario = mapper.toEntity(dto);
        validation.validarEntidade(funcionario);
        Set<Role> rolesFuncionario = roleRepository.findAllByNameIn(dto.roles().stream().map(String::toLowerCase).collect(Collectors.toSet()));
        if (rolesFuncionario.isEmpty()) throw new NaoRegistradoExcpetion("Roles não encontradas");
        funcionario.setRoles(rolesFuncionario);
        return repository.save(funcionario);
    }

    public FuncionarioResultadoDTO getFuncionarioId(Long idFuncionario){
        return mapper.toDTO(repository.findById(idFuncionario).orElseThrow(
                () -> new NaoRegistradoExcpetion("Funcionário com o id " + idFuncionario + " não existe")));
    }

    public Funcionario getFuncionarioEntity(Long id){
        return repository.findById(id).orElse(null);
    }
}
